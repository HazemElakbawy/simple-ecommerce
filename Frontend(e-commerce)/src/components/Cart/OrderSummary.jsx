import { Card } from "react-bootstrap";
import { Link } from "react-router-dom";
import { totalAmount } from "./Cart";

function OrderSummary(props) {
  let isInCartPage = props.inCartPage;
  return (
    <Card className="mt-5">
      <h3 className="p-3" style={{ backgroundColor: "#f6f6f6" }}>
        Order Summary
      </h3>
      <div className="p-3">
        <div className="d-flex justify-content-between">
          <p>Products Price</p>
          <p>{totalAmount.toFixed()}$</p>
        </div>
        <div className="d-flex justify-content-between">
          <p>Shipping</p>
          <p>30$</p>
        </div>
        <div className="d-flex justify-content-between fw-bold">
          <p>Total amount</p>
          <p>{(totalAmount + 30).toFixed()}$</p>
        </div>
      </div>
      {isInCartPage ? (
        <Link to={"/checkout"} className="m-2 btn btn-dark" variant="dark">
          Go to checkout
        </Link>
      ) : null}
    </Card>
  );
}

export default OrderSummary;
