import { Card, Col, Row, Form, Button } from "react-bootstrap";
import OrderSummary from "../Cart/OrderSummary";

function CheckoutBody() {
  return (
    <>
      <h1 className="text-center mt-3">Checkout</h1>
      <hr />
      <Row className="row-checkout">
        <Col lg={8} md={6} sm={6} className="my-5">
          <Card>
            <h3 className="p-3" style={{ backgroundColor: "#f6f6f6" }}>
              Billing address
            </h3>

            <Form className="p-3">
              <Row className="mb-3">
                <Form.Group as={Col} controlId="formGridFirst">
                  <Form.Label>First Name</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter First Name"
                    required
                  />
                </Form.Group>
                <Form.Group as={Col} controlId="formGridLast">
                  <Form.Label>Last Name</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter Last Name"
                    required
                  />
                </Form.Group>
              </Row>
              <Row className="mb-3">
                <Form.Group as={Col} controlId="formGridEmail">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter email"
                    required
                  />
                </Form.Group>
              </Row>

              <Form.Group className="mb-3" controlId="formGridAddress1">
                <Form.Label>Address</Form.Label>
                <Form.Control placeholder="1234 Main St" required />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formGridAddress2">
                <Form.Label>Address 2 (Optinal)</Form.Label>
                <Form.Control placeholder="Apartment, studio, or floor" />
              </Form.Group>

              <Row className="mb-5">
                <Form.Group as={Col} controlId="formGridCity">
                  <Form.Label>Country</Form.Label>
                  <Form.Select defaultValue="Choose..." required>
                    <option>Choose...</option>
                    <option>Egypt</option>
                  </Form.Select>
                </Form.Group>

                <Form.Group as={Col} controlId="formGridState">
                  <Form.Label>State</Form.Label>
                  <Form.Select defaultValue="Choose..." required>
                    <option>Choose...</option>
                    <option>...</option>
                  </Form.Select>
                </Form.Group>

                <Form.Group as={Col} controlId="formGridZip">
                  <Form.Label>Zip</Form.Label>
                  <Form.Control required />
                </Form.Group>
              </Row>
              <hr />
              <h3>Payment</h3>
              <Row>
                <Form.Group as={Col} controlId="formGridNameCard">
                  <Form.Label>Name On Card</Form.Label>
                  <Form.Control type="text" placeholder="" />
                </Form.Group>
                <Form.Group as={Col} controlId="formGridCreditCardNumber">
                  <Form.Label>Credit Card Number</Form.Label>
                  <Form.Control type="text" placeholder="" />
                </Form.Group>
              </Row>
              <Row className="mt-3">
                <Form.Group as={Col} controlId="formGridExpiration">
                  <Form.Label>Expiration</Form.Label>
                  <Form.Control type="text" placeholder="" />
                </Form.Group>
                <Form.Group as={Col} controlId="formGridCVV">
                  <Form.Label>CVV</Form.Label>
                  <Form.Control type="text" placeholder="" />
                </Form.Group>
              </Row>
              <Row className="p-2">
                <Button className="mt-4" variant="dark" type="submit">
                  Checkout
                </Button>
              </Row>
            </Form>
          </Card>
        </Col>
        <Col lg={4} md={6} sm={6}>
          <OrderSummary inCartPage={false} />
        </Col>
      </Row>
    </>
  );
}

export default CheckoutBody;
