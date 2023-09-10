<template>
  <button @click="click" type="button" class="btn btn-primary">Show live toast</button>
  <div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="toast-header">
        <img src="..." class="rounded me-2" alt="..." />
        <strong id="toastTitle" class="me-auto"></strong>
        <small>now</small>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
      <div id="toastBody" class="toast-body"></div>
    </div>
    <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="toast-header">
        <img src="..." class="rounded me-2" alt="..." />
        <strong id="toastTitle" class="me-auto"></strong>
        <small>now</small>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
      <div id="toastBody" class="toast-body"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Toast } from "bootstrap";

// onMounted(() => {
//   const toastLiveExample = document.getElementById("liveToast")!;
//   const toastBootstrap = new Toast(toastLiveExample);
//   toastBootstrap.show();
// });

const click = () => {
  const toastLiveExample = document.getElementById("liveToast") as HTMLElement;
  const toastBootstrap = new Toast(toastLiveExample);
  toastBootstrap.show();
};
</script>

<style scoped></style>
