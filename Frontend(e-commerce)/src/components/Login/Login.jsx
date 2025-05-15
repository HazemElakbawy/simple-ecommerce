import { Button, Container, Form, Alert } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useAuth } from "../../contexts/AuthContext"; // Import the useAuth hook

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const { signIn } = useAuth(); // Get the signIn function from the Authcontext

  const handleSubmit = async (event) => {
    event.preventDefault();
    setErrorMessage("");

    try {
      await signIn({ email, password });
    } catch (error) {
      setErrorMessage(
        error.message || "Login failed. Please check your credentials."
      );
      console.error("Login error:", error);
    }
  };

  return (
    <Container className="mb-5">
      <h1 className="text-center mt-3">Login</h1>
      <hr />
      <Form
        className="m-auto"
        style={{ width: "300px" }}
        onSubmit={handleSubmit}
      >
        {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="name@example.com"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <p>
          New Here?{" "}
          <Link style={{ color: "#21e1ff" }} to={"/register"}>
            Register
          </Link>
        </p>
        <div className="text-center">
          <Button
            variant="primary"
            type="submit"
            style={{
              background: "linear-gradient(135deg, #2c3e50 0%, #4ca1af 100%)",
              border: "none",
              padding: "8px 20px",
              fontWeight: "500",
            }}
          >
            Login
          </Button>
        </div>
      </Form>
    </Container>
  );
}

export default Login;
