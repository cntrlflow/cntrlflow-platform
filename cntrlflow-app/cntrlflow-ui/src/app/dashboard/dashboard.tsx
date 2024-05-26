import NavMenu from "@/components/sidebar/nav-menu";
import SideBar from "@/components/sidebar/sidebar";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
// import { AddReduxValue } from "@/redux/reduxStore";
// import { useEffect } from "react";
// import { useDispatch } from "react-redux";
// import { useLocation } from "react-router-dom";
import Summary from "./_components/summary";
import Configuration from "./_components/configuration";
import Environment from "./_components/environment";
import Logs from "./_components/logs";

const Dashboard = () => {
  // const location = useLocation();
  // const dispatch = useDispatch();

  // useEffect(() => {
  //   if (location.pathname != "/login") {
  //     AddReduxValue(dispatch, "previousPath", location.pathname);
  //   }
  // }, [dispatch, location.pathname]);

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
          <Tabs defaultValue="summary">
            <div className="flex items-center">
              <TabsList>
                <TabsTrigger value="summary">Summary</TabsTrigger>
                <TabsTrigger value="configuration">Configuration</TabsTrigger>
                <TabsTrigger value="environment">Environment</TabsTrigger>
                <TabsTrigger value="logs">Logs</TabsTrigger>
              </TabsList>

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
            </div>
          </Tabs>
        </main>
      </div>
    </div>
  );
};

export default Dashboard;
