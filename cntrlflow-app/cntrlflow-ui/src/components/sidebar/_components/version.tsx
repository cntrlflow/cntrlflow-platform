import { Settings } from "lucide-react";
import MenuWithToolTip from "./Menu";

const Version = () => {
  return (
    <>
      <nav className="mt-auto flex flex-col items-center gap-4 px-2 sm:py-5">
        <MenuWithToolTip
          href="#"
          name="Settings"
          icon={<Settings size={24} />}
        />
      </nav>
    </>
  );
};

export default Version;
