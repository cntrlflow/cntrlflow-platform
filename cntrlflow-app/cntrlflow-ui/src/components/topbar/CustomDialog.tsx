import React, { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Textarea } from "@/components/ui/textarea";

interface CustomDialogProps {
  title: string;
}

const CustomDialog: React.FC<CustomDialogProps> = ({ title }) => {
  const [jsonPayload, setJsonPayload] = useState<string>("");

  useEffect(() => {
    fetch("/register.json")
      .then((response) => response.json())
      .then((data) => {
        setJsonPayload(JSON.stringify(data, null, 2));
      })
      .catch((error) => console.error("Error fetching JSON:", error));
  }, []);

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const parsedData = JSON.parse(jsonPayload);
      console.log("JSON payload:", parsedData);
      // Add your submission logic here
    } catch (error) {
      console.error("Invalid JSON:", error);
    }
  };

  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button>{title}</Button>
      </DialogTrigger>
      <DialogContent className="w-full max-w-3xl mx-auto">
        <DialogHeader>
          <DialogTitle>{title}</DialogTitle>
        </DialogHeader>
        <form onSubmit={handleSubmit} className="grid gap-4 py-4">
          <div className="grid items-center gap-4">
            <Textarea
              id="jsonPayload"
              value={jsonPayload}
              onChange={(e) => setJsonPayload(e.target.value)}
              className="w-full max-h-[70vh] h-[70vh] overflow-auto"
              placeholder="Enter your JSON here..."
            />
          </div>
          <DialogFooter>
            <Button type="submit">Submit</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default CustomDialog;
