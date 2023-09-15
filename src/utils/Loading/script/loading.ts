import { isLoading } from "@/main";

export const loadImage = (path: string) => {
  isLoading.value = true;

  const image = new Image();

  image.src = path;
  image.onload = () => (isLoading.value = false);

  return image;
};
