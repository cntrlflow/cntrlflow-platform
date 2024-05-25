"use client";

import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import Summary from "./_components/summary";
import Configuration from "./_components/configuration";
import Environment from "./_components/environment";
import Logs from "./_components/logs";

//sfc
const Dashboard = () => {
  return (
    <>
      <Tabs defaultValue="summary">
        <div className="flex items-center">
          <TabsList>
            <TabsTrigger value="summary">Summary</TabsTrigger>
            <TabsTrigger value="configuration">Configuration</TabsTrigger>
            <TabsTrigger value="environment">Environment</TabsTrigger>
            <TabsTrigger value="logs">Logs</TabsTrigger>
          </TabsList>
        </div>

        <TabsContent value="summary">
          <Summary />
        </TabsContent>

        <TabsContent value="configuration">
          <Configuration />
        </TabsContent>

        <TabsContent value="environment">
          <Environment />
        </TabsContent>

        <TabsContent value="logs">
          <Logs />
        </TabsContent>
      </Tabs>
    </>
  );
};

export default Dashboard;
