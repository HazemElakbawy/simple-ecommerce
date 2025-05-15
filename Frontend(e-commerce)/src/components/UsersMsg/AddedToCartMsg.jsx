import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

export function AddedToCartMsg() {
  MySwal.fire({
    title: "Success!",
    text: "Item added to your cart.",
    icon: "success",
    iconColor: "#66bb6a",
    showConfirmButton: false,
    timer: 1500,
  });
}
