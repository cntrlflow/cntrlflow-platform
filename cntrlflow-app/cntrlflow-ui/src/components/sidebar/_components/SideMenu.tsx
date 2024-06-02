import { Home, Package, ShoppingCart, Users2, LineChart } from "lucide-react";
import MenuWithToolTip from "./Menu";

const SideMenu = () => {
  return (
    <>
      <MenuWithToolTip href="#" name="Dashboard" icon={<Home size={24} />} />
      <MenuWithToolTip
        href="#"
        name="Orders"
        icon={<ShoppingCart size={24} />}
      />
      <MenuWithToolTip href="#" name="Products" icon={<Package size={24} />} />
      <MenuWithToolTip href="#" name="Customers" icon={<Users2 size={24} />} />
      <MenuWithToolTip
        href="#"
        name="Analytics"
        icon={<LineChart size={24} />}
      />
    </>
  );
};

export default SideMenu;
