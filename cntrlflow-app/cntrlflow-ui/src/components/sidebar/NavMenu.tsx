import { Button } from "@/components/ui/button";
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet";
import {
  Home,
  PanelLeft,
  ShoppingCart,
  Package,
  Users2,
  Search,
} from "lucide-react";
import MobileMenu from "./_components/MobileMenu";
import Logo from "./_components/Logo";
import CustomBreadcrumbList from "./_components/Breadcrumb";
import { Input } from "@/components/ui/input";
import RightNav from "./_components/RightNav";

interface LinkProps {
  href: string;
  name: string;
}

interface NextBreadcrumbProps {
  links: LinkProps[];
}

const NavMenu: React.FC<NextBreadcrumbProps> = ({ links }) => {
  return (
    <>
      <header className="sticky top-0 z-30 flex h-14 items-center gap-4 border-b bg-background px-4 sm:static sm:h-auto sm:border-0 sm:bg-transparent sm:px-6">
        <Sheet>
          <SheetTrigger asChild>
            <Button size="icon" variant="outline" className="sm:hidden">
              <PanelLeft className="h-5 w-5" />
              <span className="sr-only">Toggle Menu</span>
            </Button>
          </SheetTrigger>
          <SheetContent side="left" className="sm:max-w-xs">
            <nav className="grid gap-6 text-lg font-medium">
              <Logo />
              <MobileMenu
                href="#"
                name="Dashboard"
                icon={<Home className="h-5 w-5" />}
              />
              <MobileMenu
                href="#"
                name="Orders"
                icon={<ShoppingCart className="h-5 w-5" />}
              />
              <MobileMenu
                href="#"
                name="Products"
                icon={<Package className="h-5 w-5" />}
              />
              <MobileMenu
                href="#"
                name="Customers"
                icon={<Users2 className="h-5 w-5" />}
              />
            </nav>
          </SheetContent>
        </Sheet>
        <CustomBreadcrumbList links={links} />
        <div className="relative ml-auto flex-1 md:grow-0">
          <Search className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
          <Input
            type="search"
            placeholder="Search..."
            className="w-full rounded-lg bg-background pl-8 md:w-[200px] lg:w-[336px]"
          />
        </div>
        <RightNav />
      </header>
    </>
  );
};

export default NavMenu;
