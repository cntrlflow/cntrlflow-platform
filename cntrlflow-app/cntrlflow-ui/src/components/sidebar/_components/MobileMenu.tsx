import { Link } from "react-router-dom";

interface MobileMenuProps {
  href: string;
  name: string;
  icon: React.ReactNode;
}

const MobileMenu: React.FC<MobileMenuProps> = ({ href, name, icon }) => {
  return (
    <>
      <Link
        to={href}
        className="flex items-center gap-4 px-2.5 text-muted-foreground hover:text-foreground"
      >
        {icon}
        {name}
      </Link>
    </>
  );
};

export default MobileMenu;
