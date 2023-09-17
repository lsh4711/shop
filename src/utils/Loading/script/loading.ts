import { loadCount } from "@/main";

export const loadImage = (path: string) => {
  loadCount.value++;

  const image = new Image();

  image.src = path;
  image.onload = () => loadCount.value--;

  return image;
};
