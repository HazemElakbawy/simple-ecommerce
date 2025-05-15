import "../../App.css"; // Make sure this path is correct
import { Container, Nav, Navbar, Badge, Button } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faArrowRightToBracket,
  faCartShopping,
  faUserPlus,
  faSignOutAlt, // Added for sign-out icon
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import { useAuth } from "../../contexts/AuthContext"; // Import your AuthContext

function NavbarApp({ cartItemCount = 0 }) {
  const { user, signOut } = useAuth(); // Use the useAuth hook

  // Function to get the first name
  const getFirstName = (fullName) => {
    if (!fullName) return "";
    const names = fullName.split(" ");
    return names[0];
  };

  return (
    <Navbar expand="lg" className="custom-navbar" sticky="top">
      <Container>
        <Link to={"/"} className="navbar-brand">
          <span className="brand-text">Fashion Fix</span>
        </Link>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mx-auto">
            <Link className="nav-link" to={"/"}>
              Home
            </Link>
            <Link className="nav-link" to={"/products"}>
              Products
            </Link>
            <Link className="nav-link" to={"/about"}>
              About
            </Link>
          </Nav>
          <div className="d-flex align-items-center">
            {user ? (
              // If user is logged in, show greeting and sign-out
              <>
                <span className="me-2">
                  Hello, {getFirstName(user.fullName)}
                </span>
                <Button
                  variant="outline-danger" // Use a danger variant for sign out
                  onClick={signOut}
                  className="btn-auth" // Keep consistent button styling
                >
                  <FontAwesomeIcon icon={faSignOutAlt} className="me-2" />
                  Sign Out
                </Button>
              </>
            ) : (
              // If user is not logged in, show Login and Register links
              <>
                <Link to={"/login"} className="btn btn-auth me-2">
                  <FontAwesomeIcon
                    icon={faArrowRightToBracket}
                    className="me-2"
                  />
                  Login
                </Link>
                <Link to={"/register"} className="btn btn-auth me-3">
                  <FontAwesomeIcon icon={faUserPlus} className="me-2" />
                  Register
                </Link>
              </>
            )}

            <Link to={"/cart"} className="btn btn-cart position-relative">
              <FontAwesomeIcon icon={faCartShopping} className="me-2" />
              Cart
              {cartItemCount > 0 && (
                <Badge
                  pill
                  bg="danger"
                  className="position-absolute top-0 start-100 translate-middle"
                >
                  {cartItemCount}
                </Badge>
              )}
            </Link>
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarApp;
