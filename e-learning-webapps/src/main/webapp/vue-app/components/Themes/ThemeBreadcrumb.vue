<template>
  <div class="theme-breadcrumb-wrapper">
    <div v-if="themeBreadcrumb && themeBreadcrumb.length <= 4" class="themes-tree-items d-flex">
      <div
        v-for="(theme, index) in themeBreadcrumb"
        :key="index"
        :class="themeBreadcrumb && themeBreadcrumb.length === 1 && 'single-path-element' || ''"
        class="themes-tree-item d-flex text-truncate"
        :style="`max-width: ${100 / (themeBreadcrumb.length)}%`">
        <v-tooltip max-width="300" bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              height="20px"
              min-width="45px"
              class="pa-0"
              text
              v-bind="attrs"
              v-on="on"
              @click="$emit('open-theme',theme.id)">
              <a
                class="caption text-truncate breadCrumb-link"
                :class="index < themeBreadcrumb.length-1 && 'path-clickable text-color' || 'text-sub-title not-clickable'">{{ theme.title }}</a>
            </v-btn>
          </template>
          <span class="caption">{{ theme.title }}</span>
        </v-tooltip>
        <v-icon v-if="index < themeBreadcrumb.length-1" size="18">mdi-chevron-right</v-icon>
      </div>
    </div>
    <div v-else class="themes-tree-items themes-long-path d-flex align-center">
      <div class="themes-tree-item long-path-first-item d-flex text-truncate">
        <v-tooltip max-width="300" bottom>
          <template v-slot:activator="{ on, attrs }">
            <a
              class="caption text-color text-truncate path-clickable breadCrumb-link"
              v-bind="attrs"
              v-on="on"
              @click="$emit('open-theme',themeBreadcrumb[0].id)">{{ themeBreadcrumb && themeBreadcrumb.length && themeBreadcrumb[0].title }}</a>
          </template>
          <span class="caption">{{ themeBreadcrumb && themeBreadcrumb.length && themeBreadcrumb[0].title }}</span>
        </v-tooltip>
        <v-icon size="18">mdi-chevron-right</v-icon>
      </div>
      <div class="themes-tree-item long-path-second-item d-flex">
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-icon
              v-bind="attrs"
              v-on="on"
              size="24">
              mdi-dots-horizontal
            </v-icon>
          </template>
          <p
            v-for="(theme, index) in themeBreadcrumb"
            :key="index"
            class="mb-0">
            <span v-if="index > 0 && index < themeBreadcrumb.length-2" class="caption"><v-icon size="18" class="tooltip-chevron">mdi-chevron-right</v-icon> {{ theme.title }}</span>
          </p>
        </v-tooltip>
        <v-icon class="clickable" size="18">mdi-chevron-right</v-icon>
      </div>
      <div class="themes-tree-item long-path-third-item d-flex text-truncate">
        <v-tooltip max-width="300" bottom>
          <template v-slot:activator="{ on, attrs }">
            <a
              class="caption text-color text-truncate path-clickable breadCrumb-link"
              v-bind="attrs"
              v-on="on"
              @click="$emit('open-theme',themeBreadcrumb[themeBreadcrumb.length-2].id)">{{ themeBreadcrumb[themeBreadcrumb.length-2].title }}</a>
          </template>
          <span class="caption">{{ themeBreadcrumb[themeBreadcrumb.length-2].title }}</span>
        </v-tooltip>
        <v-icon size="18">mdi-chevron-right</v-icon>
      </div>
      <div class="themes-tree-item d-flex text-truncate">
        <v-tooltip max-width="300" bottom>
          <template v-slot:activator="{ on, attrs }">
            <a
              class="caption text-color text-truncate text-sub-title breadCrumb-link"
              v-bind="attrs"
              v-on="on"
              @click="$emit('open-theme',themeBreadcrumb[themeBreadcrumb.length-1].id)">{{ themeBreadcrumb[themeBreadcrumb.length-1].title }}</a>
          </template>
          <span class="caption">{{ themeBreadcrumb[themeBreadcrumb.length-1].title }}</span>
        </v-tooltip>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    themeBreadcrumb: {
      type: Array,
      default: () => null
    }
  },
};
</script>