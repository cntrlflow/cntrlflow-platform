import {
  Breadcrumb,
  BreadcrumbItem,
  BreadcrumbLink,
  BreadcrumbList,
  BreadcrumbPage,
  BreadcrumbSeparator,
} from "@/components/ui/breadcrumb";
import Link from "next/link";
import React from "react";

interface LinkProps {
  href: string;
  name: string;
}

interface NextBreadcrumbProps {
  links: LinkProps[];
}

const CustomBreadcrumbList: React.FC<NextBreadcrumbProps> = ({ links }) => {
  return (
    <Breadcrumb className="hidden md:flex">
      <BreadcrumbList>
        {links.map((link, index) => (
          <React.Fragment key={index}>
            <BreadcrumbItem>
              <BreadcrumbLink asChild>
                <Link href={link.href}>{link.name}</Link>
              </BreadcrumbLink>
            </BreadcrumbItem>
            {index !== links.length - 1 && <BreadcrumbSeparator />}
          </React.Fragment>
        ))}
      </BreadcrumbList>
    </Breadcrumb>
  );
};

export default CustomBreadcrumbList;
