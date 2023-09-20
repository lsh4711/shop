<template>
  <div v-if="!computedIsLogin">
    <LoginModal></LoginModal>
    <SignUpModal></SignUpModal>
  </div>
</template>

<script setup lang="ts">
import LoginModal from "@/components/modal/LoginModal.vue";
import SignUpModal from "@/components/modal/SignUpModal.vue";
import { useSessionStore } from "@/store";
import { computed } from "vue";

const store = useSessionStore();

const computedIsLogin = computed(() => store.isLogin);
</script>

<style scoped></style>
