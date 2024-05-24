import NavMenu from "@/components/sidebar/nav-menu";
import SideBar from "@/components/sidebar/sidebar";
import { ChildProps } from "@/types";

const HomeLayout = ({ children }: ChildProps) => {
  return (
    <div className="flex min-h-screen w-full flex-col bg-muted/40">
      <SideBar />
      <div className="flex flex-col sm:gap-4 sm:py-4 sm:pl-14">
        <NavMenu
          links={[
            { href: "/home", name: "Home" },
            { href: "/dashboard", name: "Dashboard" },
          ]}
        />
        <main className="grid flex-1 items-start gap-4 p-4 sm:px-6 sm:py-0 md:gap-8">
          {children}
        </main>
      </div>
    </div>
  );
};

export default HomeLayout;
