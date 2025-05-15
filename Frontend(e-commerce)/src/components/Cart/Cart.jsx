import React, { useEffect } from "react";
import {
  faArrowLeft,
  faMinus,
  faPlus,
  faTrash,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button, Card, Col, Container, Row, Spinner } from "react-bootstrap";
import { Link } from "react-router-dom";
import OrderSummary from "./OrderSummary";
import { useCart } from "../../contexts/CartContext";
import { useAuth } from "../../contexts/AuthContext";

export let totalAmount;

function Cart() {
  const {
    items: cart,
    addToCart,
    removeFromCart,
    refetchCart,
    loading,
    error,
  } = useCart();
  const { user } = useAuth();

  // Fetch the cart items when the component mounts
  useEffect(() => {
    if (user) {
      refetchCart();
    }
  }, [user]);

  totalAmount = cart.reduce((total, item) => {
    return total + item.price * (item.quantity || 1);
  }, 0);

  const handleDecrease = (product) => {
    removeFromCart(product.id);
  };

  const handleIncrease = (product) => {
    addToCart(product.id, 1);
  };

  const buttonStyle = {
    background: "transparent",
    color: "#2c3e50",
    border: "1px solid #2c3e50",
    padding: "8px 16px",
    fontWeight: "500",
    transition: "all 0.3s ease",
  };

  const quantityButtonStyle = {
    background: "transparent",
    color: "#2c3e50",
    border: "1px solid #2c3e50",
    width: "38px",
    height: "38px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    transition: "all 0.3s ease",
  };

  const removeButtonStyle = {
    background: "transparent",
    color: "#dc3545",
    border: "1px solid #dc3e50",
    padding: "8px 16px",
    fontWeight: "500",
    transition: "all 0.3s ease",
  };

  if (!user) {
    return (
      <Container>
        <div className="text-center mt-5">
          <h2 className="display-6">
            Cart is Available for Signed In Users Only
          </h2>
          <p className="mt-3">
            Please <Link to="/login">sign in</Link> to view your cart.
          </p>
          <Link to={"/products"} className="btn mt-4" style={buttonStyle}>
            <FontAwesomeIcon icon={faArrowLeft} /> Continue Shopping
          </Link>
        </div>
      </Container>
    );
  }

  if (loading) {
    return (
      <Container className="text-center mt-5">
        <Spinner animation="border" variant="primary" />
      </Container>
    );
  }

  if (error) {
    return (
      <Container className="text-center mt-5">
        <p className="text-danger">Failed to load cart: {error}</p>
      </Container>
    );
  }

  if (cart.length === 0) {
    return (
      <Container>
        <div className="text-center mt-3">
          <div className="p-5" style={{ backgroundColor: "#f6f6f6" }}>
            <h2 className="display-5 mt-2">Your Cart is Empty</h2>
            <Link to={"/products"} className="btn mt-4" style={buttonStyle}>
              <FontAwesomeIcon icon={faArrowLeft} /> Continue Shopping
            </Link>
          </div>
        </div>
      </Container>
    );
  }

  return (
    <Container>
      <h1 className="text-center mt-3">Cart</h1>
      <hr />
      <Row>
        <Col lg={8} md={6} sm={6}>
          {cart.map((product) => (
            <Card
              key={`${product.id}-${product.selectedColor}-${product.selectedSize}`}
              className="my-5"
            >
              <Row className="align-items-center">
                <Col xs={3}>
                  <div className="d-flex justify-content-center p-2">
                    <Card.Img
                      variant="top"
                      src={product.pictureUrl}
                      style={{
                        width: "120px",
                        height: "150px",
                        objectFit: "contain",
                      }}
                    />
                  </div>
                </Col>
                <Col xs={5}>
                  <Card.Title className="text-center">
                    {product.name}
                  </Card.Title>
                  {product.selectedColor && product.selectedSize && (
                    <div className="text-center mt-2">
                      <span
                        className="badge me-2"
                        style={{
                          background:
                            "linear-gradient(135deg, #2c3e50 0%, #4ca1af 100%)",
                          color: "white",
                        }}
                      >
                        Color: {product.selectedColor}
                      </span>
                      <span
                        className="badge"
                        style={{
                          background:
                            "linear-gradient(135deg, #2c3e50 0%, #4ca1af 100%)",
                          color: "white",
                        }}
                      >
                        Size: {product.selectedSize}
                      </span>
                    </div>
                  )}
                  <div className="text-center mt-2">
                    <Button
                      variant="outline-danger"
                      onClick={() =>
                        removeFromCart(
                          product.id,
                          product.selectedColor,
                          product.selectedSize
                        )
                      }
                      style={removeButtonStyle}
                      className="mt-2"
                    >
                      <FontAwesomeIcon icon={faTrash} /> Remove
                    </Button>
                  </div>
                </Col>
                <Col xs={4}>
                  <div className="d-flex align-items-center justify-content-center gap-4">
                    <Button
                      variant="outline-dark"
                      onClick={() => handleDecrease(product)}
                      style={quantityButtonStyle}
                      className="quantity-btn"
                    >
                      <FontAwesomeIcon icon={faMinus} />
                    </Button>
                    <div className="d-flex flex-column align-items-center">
                      <p className="fs-4 fw-bold" style={{ margin: "0" }}>
                        {product.quantity || 1}
                      </p>
                      <p className="fw-bold">
                        ${(product.price * (product.quantity || 1)).toFixed(2)}
                      </p>
                    </div>
                    <Button
                      variant="outline-dark"
                      onClick={() => handleIncrease(product)}
                      style={quantityButtonStyle}
                      className="quantity-btn"
                    >
                      <FontAwesomeIcon icon={faPlus} />
                    </Button>
                  </div>
                </Col>
              </Row>
            </Card>
          ))}
        </Col>
        <Col lg={4} md={6} sm={6} className="mb-3">
          <OrderSummary inCartPage={true} />
        </Col>
      </Row>
    </Container>
  );
}

export default Cart;
