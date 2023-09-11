import { Toast } from "bootstrap";
import dayjs from "dayjs";

export const printToast = (
  title: string = "테스트",
  body: string = "테스트",
  color: string = "black"
) => {
  const date = dayjs().format("YYYY-MM-DD A hh시 mm분 ss초");
  const toastHtml = `<div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="toast-header">
        <!--<img src="..." class="rounded me-2" alt="..." />-->
        <strong style="color: ${color}" class="me-auto">${title}</strong>
        <small>${date}</small>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
      <div class="toast-body">${body}</div>
    </div>`;
  const toastContainer = document.getElementById("toastContainer")!;
  toastContainer.insertAdjacentHTML("beforeend", toastHtml);

  const lastToast = document.querySelector(".toast:last-child")!;
  const toast = new Toast(lastToast);

  toast.show();
};
