import React, { useState } from "react";
import { Button, Card, Col, Row, Spinner } from "react-bootstrap";
import { Link } from "react-router-dom";
import { AddedToCartMsg } from "../UsersMsg/AddedToCartMsg";
import { useCart } from "../../contexts/CartContext";
import { config } from "../../config";

function Product({ products }) {
  const { addToCart } = useCart();
  const [loadingProductId, setLoadingProductId] = useState(null);
  const [cartError, setCartError] = useState(null);

  const buttonStyle = {
    background: "linear-gradient(135deg, #2c3e50 0%, #4ca1af 100%)",
    border: "none",
    padding: "8px 20px",
    fontWeight: "500",
    color: "white",
  };

  const handleAddToCart = async (product) => {
    if (!addToCart) {
      console.error("addToCart function is undefined. Check your CartContext.");
      return;
    }

    setLoadingProductId(product.id);
    setCartError(null);

    addToCart(product.id, 1);

    AddedToCartMsg();
  };

  if (!Array.isArray(products) || products.length === 0) {
    return (
      <div className="text-center mt-5">
        <Spinner animation="border" variant="dark" />
        <p>Loading products...</p>
      </div>
    );
  }

  return (
    <Row className="justify-content-center">
      {products.map((product) => (
        <Col key={product.id} lg={4} md={6} sm={12} className="p-3">
          <Card>
            <div className="d-flex justify-content-center p-2">
              <Card.Img
                variant="top"
                src={product.pictureUrl || product.image}
                style={{ width: "200px", height: "250px", objectFit: "cover" }}
                alt={product.name || product.title}
              />
            </div>
            <Card.Body>
              <Card.Title className="text-center">
                {(product.name || product.title).slice(0, 20)}...
              </Card.Title>
              <Card.Text>
                {(product.description || "").slice(0, 60)}...
              </Card.Text>
              <hr />
              <Card.Text className="fs-5 text-center">
                {product.price}$
              </Card.Text>
              <hr />
              <div className="d-flex justify-content-around">
                <Link
                  to={`/products/${product.id}`}
                  className="btn"
                  style={buttonStyle}
                >
                  More..
                </Link>
                <Button
                  style={buttonStyle}
                  onClick={() => handleAddToCart(product)}
                  disabled={loadingProductId === product.id}
                >
                  {loadingProductId === product.id ? (
                    <>
                      <Spinner size="sm" className="me-2" />
                      Adding...
                    </>
                  ) : (
                    "Add To Cart"
                  )}
                </Button>
              </div>
              {cartError && (
                <p className="text-danger mt-2 text-center">
                  Error: {cartError}
                </p>
              )}
            </Card.Body>
          </Card>
        </Col>
      ))}
    </Row>
  );
}

export default Product;
