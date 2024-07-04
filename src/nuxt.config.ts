// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },

  ssr: false,
  devServer: {
    host: '0.0.0.0',
    port: 3005,
  },

  experimental: {
    appManifest: false,
  },

  vite: {
    envPrefix: ['VITE_', 'TAURI_'],
    server: {
      fs: {
        strict: false,
      },
      strictPort: true,
      hmr: {
        protocol: 'ws',
        host: '0.0.0.0',
        port: 5183,
      }
    }
  },

  compatibilityDate: '2024-07-04',
})
