import TopBar from "@/components/topbar/topbar";
import HomeCard from "./_components/home-card";
import { DollarSign, Search } from "lucide-react";
import { Input } from "@/components/ui/input";
import CustomTableWithLink from "./_components/custom-table-with-link";

const Home = () => {
  const headers = ["Cluster Name", "Total Services", "Total Servers", "Email"];
  const data = [
    [
      {
        value: "CNTRLFLOW-DEV",
        underline: true,
        href: "/dashboard",
      },
      { value: "3" },
      {
        value: "9",
      },
      {
        value: "cntrlflow@gmail.com",
      },
    ],
    [
      {
        value: "CNTRLFLOW-QA",
        underline: true,
        href: "/dashboard",
      },
      { value: "3" },
      {
        value: "9",
      },
      {
        value: "cntrlflow@gmail.com",
      },
    ],
    [
      {
        value: "CNTRLFLOW-PROD",
        underline: true,
        href: "/dashboard",
      },
      { value: "3" },
      {
        value: "9",
      },
      {
        value: "cntrlflow@gmail.com",
      },
    ],
  ];

  return (
    <div>
      <TopBar />
      <main className="flex flex-1 flex-col gap-4 p-4 md:gap-8 md:p-8">
        <div className="grid gap-8 md:grid-cols-2 md:gap-8 lg:grid-cols-3">
          <HomeCard
            xChunk="chunk-0"
            name="Total Clusters"
            icon={<DollarSign className="h-4 w-4 text-muted-foreground" />}
            metrics="$45,231.89"
          />
          <HomeCard
            xChunk="chunk-1"
            name="Total Servers"
            icon={<DollarSign className="h-4 w-4 text-muted-foreground" />}
            metrics="$45,231.89"
          />
          <HomeCard
            xChunk="chunk-2"
            name="Total Revenue"
            icon={<DollarSign className="h-4 w-4 text-muted-foreground" />}
            metrics="$45,231.89"
          />
        </div>

        <div className="w-full">
          <form className="ml-auto flex-1 sm:flex-initial">
            <div className="relative">
              <Search className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
              <Input
                type="search"
                placeholder="Search clusters"
                className="pl-8 w-full"
              />
            </div>
          </form>
        </div>

        <div className="w-full">
          <CustomTableWithLink headers={headers} data={data} />
        </div>
      </main>
    </div>
  );
};

export default Home;
