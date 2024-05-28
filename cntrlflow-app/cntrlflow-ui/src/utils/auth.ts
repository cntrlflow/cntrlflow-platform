import { VALIDATE_URL } from "@/Constants";

export const checkAuth = async (skip = false): Promise<boolean> => {
  if (skip) {
    return true;
  }
  try {
    const response = await fetch(VALIDATE_URL, {
      method: "GET",
      credentials: "include",
    });

    if (response.ok) {
      const data = await response.json();
      return data.status === "success";
    } else {
      return false;
    }
  } catch (error) {
    console.error("Error checking authentication", error);
    return false;
  }

  return false;
};
