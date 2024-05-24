import React from "react";

import {
  Table,
  TableBody,
  TableCell,
  TableFooter,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

interface TableHeaderProps {
  headers: string[];
}

interface TableRowProps {
  data: string[];
}

interface TableProps {
  headers: string[];
  data: string[][];
}

const CustomTableHeader: React.FC<TableHeaderProps> = ({ headers }) => (
  <TableHeader>
    <TableRow>
      {headers.map((header, index) => (
        <TableHead key={index}>{header}</TableHead>
      ))}
    </TableRow>
  </TableHeader>
);

const CustomTableRow: React.FC<TableRowProps> = ({ data }) => (
  <TableRow>
    {data.map((cell, index) => (
      <TableCell key={index}>{cell}</TableCell>
    ))}
  </TableRow>
);

const CustomTable: React.FC<TableProps> = ({ headers, data }) => (
  <Table>
    <CustomTableHeader headers={headers} />
    <TableBody>
      {data.map((rowData, index) => (
        <CustomTableRow key={index} data={rowData} />
      ))}
    </TableBody>
  </Table>
);

export default CustomTable;
