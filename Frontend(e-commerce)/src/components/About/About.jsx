import { Card, Col, Container, Row } from "react-bootstrap";

function About() {
  return (
    <Container className="mb-5">
      <h1 className="text-center mt-3">About Us</h1>
      <hr />
      <p className="fs-5 text-black-50">
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Nostrum facere
        doloremque veritatis odit similique sequi. Odit amet fuga nam quam quasi
        facilis sed doloremque saepe sint perspiciatis explicabo totam vero quas
        provident ipsam, veritatis nostrum velit quos recusandae est mollitia
        esse fugit dolore laudantium. Ex vel explicabo earum unde eligendi autem
        praesentium, doloremque distinctio nesciunt.
      </p>
      <h2 className="text-center mt-5 mb-3">Our Products</h2>
      <Row>
        <Col className="m-2">
          <Card>
            <Card.Img
              variant="top"
              src="https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg"
            />
            <Card.Title className="text-center p-3">Mens's Clothing</Card.Title>
          </Card>
        </Col>
        <Col className="m-2">
          <Card>
            <Card.Img
              variant="top"
              src="https://stayyclassyy.weebly.com/uploads/5/1/6/0/51607479/9995846_orig.png"
            />
            <Card.Title className="text-center p-3">
              Women's Clothing
            </Card.Title>
          </Card>
        </Col>
        <Col className="m-2">
          <Card>
            <Card.Img
              variant="top"
              src="https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg"
            />
            <Card.Title className="text-center p-3">Jewelery</Card.Title>
          </Card>
        </Col>
        <Col className="m-2">
          <Card>
            <Card.Img
              variant="top"
              src="https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg"
            />
            <Card.Title className="text-center p-3">Electronics</Card.Title>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default About;
