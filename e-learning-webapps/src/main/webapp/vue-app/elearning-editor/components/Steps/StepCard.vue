<template>
  <div>
    <v-card 
      :id="`step-${step.id}`"
      class="mb-2 pb-4"
      flat>
      <v-card-actions>
        <v-spacer />
        <v-icon
          class="text-sub-title d-block"
          @click="displayActionMenu = !displayActionMenu">
          mdi-dots-vertical
        </v-icon>
        <v-menu
          v-model="displayActionMenu"
          :attach="`#step-${step.id}`"
          content-class="step_card_menu"
          transition="slide-x-reverse-transition"
          offset-y
          offset-x>
          <v-list class="card_menu_list" dense>
            <v-list-item class="px-2 py-1" @click="updateStep()">
              <v-list-item-title class="menu_list_items">
                <v-icon class="primary--text" size="20">mdi-pencil</v-icon>
                <span class="tuto_menu_text">{{ $t('addon.elearning.step.update.label') }}</span>
              </v-list-item-title>
            </v-list-item>
            <v-list-item class="px-2 py-1" @click="deleteStep()">
              <v-list-item-title class="menu_list_items">
                <v-icon class="primary--text" size="20">mdi-delete</v-icon>
                <span class="tuto_menu_text">{{ $t('addon.elearning.step.delete.label') }}</span>
              </v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-card-actions>
      <v-card-text class="text-center">
        {{ step.title }}
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    tutorial: {
      type: Object,
      default: null
    },
    step: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      displayActionMenu: false,
    };
  },
  created() {
    $(document).on('mousedown', () => {
      if (this.displayActionMenu) {
        window.setTimeout(() => {
          this.displayActionMenu = false;
        }, 200);
      }
    });
  },
  methods: {
    updateStep() {
      this.$root.$emit('update-step', this.step.order);
    },
    deleteStep() {
      this.$tutoService.deleteStep(this.step.id).then(() => {
        this.$emit('step-deleted');
        this.$root.$emit('step-deleted', this.step.order);
      });
      
    },
  },
};
</script>