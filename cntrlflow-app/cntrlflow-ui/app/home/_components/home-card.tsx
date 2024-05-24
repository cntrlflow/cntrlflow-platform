import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";

interface CardProps {
  xChunk: string;
  icon: React.ReactNode;
  name: string;
  metrics: string;
}

const HomeCard: React.FC<CardProps> = ({ xChunk, icon, name, metrics }) => {
  return (
    <>
      <Card x-chunk={xChunk}>
        <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
          <CardTitle className="text-sm font-medium">{name}</CardTitle>
          {icon}
        </CardHeader>
        <CardContent>
          <div className="text-2xl font-bold">{metrics}</div>
        </CardContent>
      </Card>
    </>
  );
};

export default HomeCard;
