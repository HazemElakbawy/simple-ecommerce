import { createContext, useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { config } from "../config";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserData = async () => {
      const token = localStorage.getItem("accessToken");

      if (token) {
        try {
          // Assuming your backend provides user info with the token
          const response = await fetch(`${config.apiUrl}/v1/users`, {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });

          if (!response.ok) {
            if (response.status === 401) {
              signOut(); // Clear invalid token
            }
            throw new Error("Failed to fetch user data");
          }

          const userData = await response.json();
          setUser({
            email: userData.data.email,
            fullName: userData.data.fullName,
          });
        } catch (error) {
          console.error(error);
          signOut();
        }
      }
    };

    fetchUserData();
  }, []);

  const signIn = async (credentials) => {
    try {
      const loginResponse = await fetch(`${config.apiUrl}/v1/auth/sign-in`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: credentials.email,
          password: credentials.password,
        }),
      });

      if (!loginResponse.ok) {
        const errorData = await loginResponse.json();
        console.error("Login failed:", errorData);
        throw new Error(errorData.message || "Login failed");
      }

      const loginData = await loginResponse.json();
      const accessToken = loginData.data.accessToken;

      localStorage.setItem("accessToken", accessToken);

      // Fetch user details immediately after successful login
      const userResponse = await fetch(`${config.apiUrl}/v1/users`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });

      if (!userResponse.ok) {
        throw new Error("Failed to fetch user data after login");
      }

      const userData = await userResponse.json();
      setUser({
        email: userData.data.email,
        fullName: userData.data.fullName,
      });

      navigate("/");
    } catch (error) {
      console.error(error);
      signOut();
      throw error; // Propagate error to UI
    }
  };

  const signOut = () => {
    setUser(null);
    localStorage.removeItem("accessToken");
    // Optionally redirect to the login page after sign out
    navigate("/login");
  };

  return (
    <AuthContext.Provider value={{ user, signIn, signOut }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
}
