import Logo from "./_components/logo";
import SideMenu from "./_components/side-menu";
import Version from "./_components/version";

const SideBar = () => {
  return (
    <>
      <aside className="fixed inset-y-0 left-0 z-10 hidden w-14 flex-col border-r bg-background sm:flex">
        <nav className="flex flex-col items-center gap-4 px-2 sm:py-5">
          <Logo />
          <SideMenu />
          <Version />
        </nav>
      </aside>
    </>
  );
};

export default SideBar;
