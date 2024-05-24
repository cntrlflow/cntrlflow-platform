import React from "react";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

import Link from "next/link";

interface TableCellProps {
  value: string;
  underline?: boolean;
  href?: string;
}

interface TableRowProps {
  data: TableCellProps[];
}

interface TableHeaderProps {
  headers: string[];
}

interface TableRowData {
  value: string;
  underline?: boolean;
  href?: string;
}

interface TableProps {
  headers: string[];
  data: TableRowData[][];
}

const CustomTableCell: React.FC<TableCellProps> = ({
  value,
  underline = false,
  href,
}) => {
  const content =
    underline && href ? (
      <Link href={href}>
        <p style={{ textDecoration: "underline" }}>{value}</p>
      </Link>
    ) : (
      value
    );

  return <TableCell>{content}</TableCell>;
};

const CustomTableRow: React.FC<TableRowProps> = ({ data }) => (
  <TableRow>
    {data.map(({ value, underline, href }, index) => (
      <CustomTableCell
        key={index}
        value={value}
        underline={underline}
        href={href}
      />
    ))}
  </TableRow>
);

const CustomTableHeader: React.FC<TableHeaderProps> = ({ headers }) => (
  <TableHeader>
    <TableRow>
      {headers.map((header, index) => (
        <TableHead key={index}>{header}</TableHead>
      ))}
    </TableRow>
  </TableHeader>
);

const CustomTableWithLink: React.FC<TableProps> = ({ headers, data }) => (
  <Table>
    <CustomTableHeader headers={headers} />
    <TableBody>
      {data.map((rowData, index) => (
        <CustomTableRow key={index} data={rowData} />
      ))}
    </TableBody>
  </Table>
);

export default CustomTableWithLink;
