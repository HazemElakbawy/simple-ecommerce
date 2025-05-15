import { Form, Button, Container, Alert } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { config } from "../../config";

function Register() {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatPassword, setRepeatPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setErrorMessage("");
    setSuccessMessage("");

    if (password !== repeatPassword) {
      setErrorMessage("Passwords do not match.");
      return;
    }

    try {
      const response = await fetch(`${config.apiUrl}/v1/auth/sign-up`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          fullName: fullName,
          email: email,
          password: password,
        }),
      });

      const data = await response.json();

      if (response.ok) {
        setSuccessMessage(data.message);
        setTimeout(() => {
          navigate("/login");
        }, 1500);
      } else {
        setErrorMessage(
          data.message || "Registration failed. Please try again."
        );
      }
    } catch (error) {
      console.error("Registration error:", error);
      setErrorMessage("An unexpected error occurred. Please try again later.");
    }
  };

  return (
    <Container className="mb-5">
      <h1 className="text-center mt-3">Register</h1>
      <hr />
      <Form
        className="m-auto"
        style={{ width: "300px" }}
        onSubmit={handleSubmit}
      >
        {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}
        {successMessage && <Alert variant="success">{successMessage}</Alert>}

        <Form.Group className="mb-3" controlId="formBasicText">
          <Form.Label>Full Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Your Name"
            required
            value={fullName}
            onChange={(e) => setFullName(e.target.value)}
          />
        </Form.Group>

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

        <Form.Group className="mb-3" controlId="formBasicRepeatPassword">
          <Form.Label>Repeat Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Repeat Password"
            required
            value={repeatPassword}
            onChange={(e) => setRepeatPassword(e.target.value)}
          />
        </Form.Group>

        <p>
          Already has an account?{" "}
          <Link style={{ color: "#21e1ff" }} to={"/login"}>
            Login
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
            Register
          </Button>
        </div>
      </Form>
    </Container>
  );
}

export default Register;
