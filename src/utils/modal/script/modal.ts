import { Modal } from "bootstrap";

export const showModal = (elementId: string) => {
  const custom = document.getElementById(elementId)!;
  const modal = new Modal(custom);

  modal.show();
};
