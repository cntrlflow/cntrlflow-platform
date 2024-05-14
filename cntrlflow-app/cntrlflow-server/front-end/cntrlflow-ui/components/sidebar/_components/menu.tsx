import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "@/components/ui/tooltip";

import Link from "next/link";

interface MenuWithToolTipProps {
  href: string;
  name: string;
  icon: React.ReactNode;
}

const MenuWithToolTip: React.FC<MenuWithToolTipProps> = ({
  href,
  name,
  icon,
}) => {
  return (
    <>
      <TooltipProvider>
        <Tooltip>
          <TooltipTrigger asChild>
            <Link
              href={href}
              className="flex h-9 w-9 items-center justify-center rounded-lg text-muted-foreground transition-colors hover:text-foreground md:h-8 md:w-8"
            >
              {icon}
              <span className="sr-only">{name}</span>
            </Link>
          </TooltipTrigger>
          <TooltipContent side="right">{name}</TooltipContent>
        </Tooltip>
      </TooltipProvider>
    </>
  );
};

export default MenuWithToolTip;
