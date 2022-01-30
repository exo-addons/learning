<template>
  <v-app>
    <div
      id="eLearningEditor"
      class="eLearningEditor width-full">
      <div class="eLearning-topbar">
        <div class="eLearningActions white">
          <div class="eLearningFormButtons d-inline-flex flex-wrap width-full px-3 py-4 ma-0">
            <div class="eLearningFormLeftActions d-inline-flex align-center me-10">
              <img :src="srcImageStep">
              <span class="eLearningFormTitle ps-2">{{ eLearningFormTitle }}</span>
            </div>
            <div class="eLearningFormRightActions pr-7">
              <span class="draftSavingStatus mr-7">{{ draftSavingStatus }}</span>
              <div class="settings-archive-btns mr-4">
                <v-btn
                  icon
                  @click="openSettings">
                  <v-icon>settings</v-icon>
                </v-btn>
                <v-btn
                  icon
                  @click="archiveTutorial">
                  <v-icon>inventory</v-icon>
                </v-btn>
              </div>
              <button
                id="eLearningUpdateAndPost"
                class="btn btn-primary primary px-6 py-1"
                @click="publishTutorial()">
                {{ $t('addon.elearning.editor.button.publish') }}
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="eLearning-content-wrapper pa-6">
        <div class="eLearning-content">
          <form class="step-form">
            <v-row class="mx-0 pt-2 pl-2">
              <v-col class="top-bar" cols="2">
                <v-btn
                  class="primary--text preview-btn pl-4 pr-8"
                  outlined
                  @click="preview">
                  <v-icon left>find_in_page</v-icon>
                  {{ $t('addon.elearning.editor.label.preview') }}
                </v-btn>
              </v-col>
              <v-col>
                <input
                  id="stepTitle"
                  ref="stepTitle"
                  v-model="step.title"
                  :placeholder="stepTitlePlaceHolder"
                  type="text"
                  class="py-0 px-1 mt-5 mb-0">
              </v-col>
            </v-row>
            <v-row class="content-dashboard mx-0 pa-4">
              <v-col class="group-steps" cols="2">
                <step-card-list
                  :tutorial="tutorial" />
                <v-card 
                  class="add-step-button-wrapper pa-2"
                  :disabled="!canAddStep"
                  flat
                  @click="addStep">
                  <v-card-text 
                    class="add-step-button pa-6 text-center"
                    :class="!canAddStep ? 'disabled--text' : 'primary--text'">
                    <v-icon 
                      size="36"
                      :class="!canAddStep ? 'disabled--text' : 'primary--text'">
                      mdi-plus
                    </v-icon>
                  </v-card-text>
                </v-card>
                <v-row class="text-sub-title">
                  <v-spacer />
                  <span>{{ $t('addon.elearning.editor.label.addStep') }}</span>
                  <v-spacer />
                </v-row>
              </v-col>
              <v-col class="pt-0 pb-0">
                <v-row class="step-media-selector mx-0">
                  <v-col>
                    <div 
                      class="media"
                      @click="addOnlineVideo">
                      <div>
                        <p>{{ $t('addon.elearning.editor.label.addVideo') }}</p>
                        <v-icon large>movie</v-icon>
                        <p>Youtube, Vimeo or Dailymotion</p>                        
                      </div>
                    </div>
                  </v-col>
                  <v-col>
                    <div 
                      class="media"
                      @click="addPicture">
                      <div>
                        <p>{{ $t('addon.elearning.editor.label.addPicture') }}</p>
                        <v-icon large>image</v-icon>
                      </div>
                    </div>
                  </v-col>
                </v-row>
                <v-row class="mx-0">
                  <div class="formInputGroup white overflow-auto flex step-content-wrapper">
                    <div id="stepTop" class="width-full"></div>
                    <textarea
                      id="stepContent"
                      v-model="step.content"
                      :placeholder="$t('addon.elearning.editor.label.step.content')"
                      class="stepFormInput"
                      name="stepContent">
                     </textarea>
                  </div>
                </v-row>
              </v-col>
            </v-row>
          </form>
        </div>
      </div>
    </div>
  </v-app>
</template>

<script>
export default {
  data() {
    return {
      step: {
        id: '',
        title: '',
        content: '',
        mediaLink: '',
        imageFileId: '',
        tutorialId: '',
      },
      actualStep: {
        id: '',
        title: '',
        content: '',
        mediaLink: '',
        imageFileId: '',
        tutorialId: '',
      },
      actualPersistedStep: {
        id: '',
        title: '',
        content: '',
        mediaLink: '',
        imageFileId: '',
        tutorialId: '',
      },
      spaceId: '',
      theme: null,
      tutorial: {},
      srcImageStep: '/exo-elearning/images/tutorial.png',
      initActualStepDone: false,
      initDone: false,
      postingStep: false,
      publishingTutorial: false,
      saveDraft: '',
      savingDraft: false,
      draftSavingStatus: '',
      autoSaveDelay: 1000,
    };
  },
  computed: {
    stepOrder() {
      return this.step.id &&  this.step.order || this.tutorial && this.tutorial.stepsIds && this.tutorial.stepsIds.length + 1 || 1;
    },
    stepTitlePlaceHolder() {
      return this.$t('addon.elearning.editor.label.step.title', {0: this.stepOrder});
    },
    eLearningFormTitle() {
      return this.tutorial && this.tutorial.title;
    },
    initCompleted() {
      return this.initDone && this.initActualStepDone;
    },
    canAddStep() {
      return (this.step.title !== this.actualPersistedStep.title || this.step.content !== this.actualPersistedStep.content) || 
        ((this.step.title || this.step.content) && this.step.title === this.actualPersistedStep.title && this.step.content === this.actualPersistedStep.content);
    },
  },
  watch: {
    'step.title'() {
      if (this.step.title !== this.actualStep.title) {
        this.autoSave();
      }
    },
    'step.content'() {
      if (this.step.content !== this.actualStep.content) {
        this.autoSave();
      }
    },
  },
  created() {
    window.addEventListener('beforeunload', () => {
      if (!this.postingStep && this.step.id) {
        const currentDraft = localStorage.getItem(`draftStepId-${this.step.id}`);
        if (currentDraft) {
          this.removeLocalStorageCurrentDraft();
          const draftToPersist = JSON.parse(currentDraft);
          this.persistStep(draftToPersist);
        }
      }
    });
    const queryPath = window.location.search;
    const urlParams = new URLSearchParams(queryPath);
    if (urlParams.has('spaceId')) {
      this.spaceId = urlParams.get('spaceId');
    }
    if (urlParams.has('themeId')) {
      const themeId = urlParams.get('themeId');
      this.getTheme(themeId);
    }
    if (urlParams.has('tutorialId')) {
      const tutorialId = urlParams.get('tutorialId');
      this.getTutorial(tutorialId);
    }
    
    this.$root.$on('update-step', stepOrder => {
      this.getTutorialStepByOrder(this.tutorial.id, stepOrder);
    });

    this.$root.$on('step-deleted', stepOrder => {
      if (this.step.id && this.step.order === stepOrder) {
        if (this.step.order === 1) {
          this.resetData();
        } else if (this.step.order > 1) {
          this.getTutorialStepByOrder(this.tutorial.id, stepOrder - 1);
        }
      } else if (this.step.id && this.step.order > stepOrder) {
        this.getTutorialStepByOrder(this.tutorial.id, this.step.order - 1);
      }
    });
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.initCKEditor();
      const elementNewTop = document.getElementById('stepTop');
      elementNewTop.classList.add('darkComposerEffect');
      this.setToolBarEffect();
      this.initDone = true;
    },
    getTheme(id) {
      this.$themeService.getThemeById(id).then(theme => {
        this.theme = theme;
      }).catch((e) => console.error('Error when retrieving theme', e));
    },
    getTutorial(id) {
      this.$tutoService.getTutorialById(id).then(tutorial => {
        this.tutorial = tutorial;
        if (this.tutorial.stepsIds.length > 0) {
          this.getLatestStep(this.tutorial.id);
        } else {
          this.initActualStepDone = true;
        }
      }).catch(e => {
        console.error('Error when retrieving tutorial', e);
      });
    },
    getLatestStep(tutorialId) {
      this.$tutoService.getTutorialStepByOrder(tutorialId, this.tutorial.stepsIds.length).then(step => {
        if (step) {
          this.fillStep(step);
        } else {
          this.resetData();
        }
      });
    },
    getTutorialStepByOrder(tutorialId, order) {
      this.resetData();
      this.$tutoService.getTutorialStepByOrder(tutorialId, order).then(step => {
        if (step) {
          this.fillStep(step);
        } else {
          this.resetData();
        }
      });
    },
    fillStep(data) {
      if (data) {
        this.step = data;
        CKEDITOR.instances['stepContent'].setData(data.content);
        this.actualPersistedStep = {
          id: this.step.id,
          title: this.step.title,
          content: this.step.content,
          mediaLink: this.step.mediaLink,
          imageFileId: this.step.imageFileId,
          order: this.step.order,
          tutorialId: this.step.tutorialId,
        };
        this.actualStep = {
          id: this.step.id,
          title: this.step.title,
          content: this.step.content,
          mediaLink: this.step.mediaLink,
          imageFileId: this.step.imageFileId,
          order: this.step.order,
          tutorialId: this.step.tutorialId,
        };
        this.initActualStepDone = true;
      }
    },
    autoSave() {
      // No draft saving if init not done or in edit mode for the moment
      if (!this.initCompleted) {
        return;
      }
      // if the Step is being posted, no need to autosave anymore
      if (this.postingStep) {
        return;
      }
      // if the tutorial is being published, no need to autosave anymore
      if (this.publishingTutorial) {
        return;
      }

      clearTimeout(this.saveDraft);
      this.saveDraft = setTimeout(() => {
        this.savingDraft = true;
        this.draftSavingStatus = this.$t('step.draft.savingDraftStatus');
        this.$nextTick(() => {
          this.saveStepDraft();
        });
      }, this.autoSaveDelay);
    },
    saveStepDraft() {
      const draftStep = {
        id: this.step.id ? this.step.id : '',
        title: this.step.title,
        content: this.step.content,
        mediaLink: this.step.mediaLink,
        imageFileId: this.step.imageFileId,
        order: this.stepOrder,
        tutorialId: this.step.tutorialId,
      };

      if (this.step.title || this.step.content) {
        // if draft step not created persist it only the first time else update it in browser's localStorage
        if (this.step.id) {
          this.step.order = this.stepOrder;
          localStorage.setItem(`draftStepId-${this.step.id}`, JSON.stringify(this.step));
          this.actualStep = {
            title: draftStep.title,
            content: draftStep.content,
          };
          setTimeout(() => {
            this.draftSavingStatus = this.$t('step.draft.savedDraftStatus');
          }, this.autoSaveDelay / 2);
        } else {
          this.persistStep(draftStep);
        }
      } else {
        // delete draft
        this.deleteStep();
      }
    },
    persistStep(step, reset) {
      if (this.step.title || this.step.content) {
        this.postingStep = true;
        if (!this.step.id) {
          this.$tutoService.saveStep(step, this.tutorial.id).then(savedStep => {
            this.addToTutorialStepIds(savedStep.id);
            this.actualPersistedStep = {
              id: savedStep.id,
              title: savedStep.title,
              content: savedStep.content,
              mediaLink: savedStep.mediaLink,
              imageFileId: savedStep.imageFileId,
              order: savedStep.order,
              tutorialId: savedStep.tutorialId,
            };
            this.actualStep = {
              id: savedStep.id,
              title: savedStep.title,
              content: savedStep.content,
              mediaLink: savedStep.mediaLink,
              imageFileId: savedStep.imageFileId,
              order: savedStep.order,
              tutorialId: savedStep.tutorialId,
            };
            // TODO consider attachments
            this.step = savedStep;
            localStorage.setItem(`draftStepId-${this.step.id}`, JSON.stringify(savedStep));
          }).then(() => {
            this.postingStep = false;
            this.savingDraft = false;
            setTimeout(() => {
              this.draftSavingStatus = this.$t('step.draft.savedDraftStatus');
            }, this.autoSaveDelay / 2);
            if (reset) {
              this.resetData();
            }
          }).catch(e => {
            console.error('Error when saving step', e);
          });
        } else {
          this.$tutoService.updateStep(step).then(() => {
            this.postingStep = false;
            this.savingDraft = false;
            setTimeout(() => {
              this.draftSavingStatus = this.$t('step.draft.savedDraftStatus');
            }, this.autoSaveDelay / 2);
            if (reset) {
              this.removeLocalStorageCurrentDraft();
              this.resetData();
            }
          }).catch(e => {
            console.error('Error when saving step', e);
          });
        }
      }
    },
    deleteStep() {
      if (this.step.id) {
        this.removeLocalStorageCurrentDraft();
        this.$tutoService.deleteStep(this.step.id).then(() => {
          this.removeFromTutorialStepIds(this.step.id);
          this.draftSavingStatus = '';
          //re-initialize data
          this.step = {
            id: '',
            title: '',
            content: '',
            mediaLink: '',
            imageFileId: '',
            order: '',
            tutorialId: this.tutorial.id,
          };
          this.actualStep = {
            id: '',
            title: '',
            content: '',
            mediaLink: '',
            imageFileId: '',
            order: '',
            tutorialId: this.tutorial.id,
          };
        }).catch(e => {
          console.error('Error when deleting draft step', e);
        });
      }
    },
    removeLocalStorageCurrentDraft() {
      const currentDraft = localStorage.getItem(`draftStepId-${this.step.id}`);
      if (currentDraft) {
        localStorage.removeItem(`draftStepId-${this.step.id}`);
      }
    },
    initCKEditor: function () {
      if (CKEDITOR.instances['stepContent'] && CKEDITOR.instances['stepContent'].destroy) {
        CKEDITOR.instances['stepContent'].destroy(true);
      }

      CKEDITOR.dtd.$removeEmpty['i'] = false;
      let extraPlugins = 'sharedspace,simpleLink,selectImage,font,justify,widget,contextmenu,tabletools,tableresize';
      const windowWidth = $(window).width();
      const windowHeight = $(window).height();
      if (windowWidth > windowHeight && windowWidth < this.SMARTPHONE_LANDSCAPE_WIDTH) {
        // Disable suggester on smart-phone landscape
        extraPlugins = 'simpleLink,selectImage';
      }
      CKEDITOR.addCss('.cke_editable { font-size: 14px;}');
      CKEDITOR.addCss('.placeholder { color: #a8b3c5!important;}');

      // this line is mandatory when a custom skin is defined

      CKEDITOR.basePath = '/commons-extension/ckeditor/';
      const self = this;

      $('textarea#stepContent').ckeditor({
        customConfig: '/commons-extension/ckeditorCustom/config.js',
        extraPlugins: extraPlugins,
        removePlugins: 'image,confirmBeforeReload,maximize,resize',
        allowedContent: true,
        spaceURL: self.spaceURL,
        toolbarLocation: 'top',
        extraAllowedContent: 'table[!summary]; img[style,class,src,referrerpolicy,alt,width,height]; span(*)[*]{*}; span[data-atwho-at-query,data-atwho-at-value,contenteditable]; a[*];i[*];',
        removeButtons: '',
        toolbar: [
          {name: 'format', items: ['Format']},
          {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', '-', 'RemoveFormat']},
          {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Blockquote']},
          {name: 'fontsize', items: ['FontSize']},
          {name: 'colors', items: ['TextColor']},
          {name: 'align', items: ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']},
          {name: 'insert'},
          {name: 'links', items: ['simpleLink', 'InsertOptions']},
        ],
        format_tags: 'p;h1;h2;h3',
        autoGrow_minHeight: self.stepFormContentHeight,
        height: self.stepFormContentHeight,
        bodyClass: 'stepContent',
        dialog_noConfirmCancel: true,
        sharedSpaces: {
          top: 'stepTop'
        },
        on: {
          instanceReady: function (evt) {
            self.step.content = evt.editor.getData();
            self.actualStep.content = evt.editor.getData();
            CKEDITOR.instances['stepContent'].removeMenuItem('linkItem');
            CKEDITOR.instances['stepContent'].removeMenuItem('selectImageItem');


            CKEDITOR.instances['stepContent'].contextMenu.addListener(function (element) {
              if (element.getAscendant('table', true)) {
                return {
                  tableProperties: CKEDITOR.TRISTATE_ON
                };
              }
            });
            CKEDITOR.instances['stepContent'].addCommand('tableProperties', {
              exec: function () {
                if (CKEDITOR.instances['stepContent'].elementPath() && CKEDITOR.instances['stepContent'].elementPath().contains('table', 1)) {
                  const tableSummary = CKEDITOR.instances['stepContent'].elementPath().contains('div', 1).$.firstChild.innerText;
                  const table = CKEDITOR.instances['stepContent'].elementPath().contains('table', 1).getAttributes();
                  self.$refs.stepTablePlugins.open(table, tableSummary);
                }

              }
            });
            $(CKEDITOR.instances['stepContent'].document.$)
              .find('.atwho-inserted')
              .each(function () {
                $(this).on('click', '.remove', function () {
                  $(this).closest('[data-atwho-at-query]').remove();
                });
              });
          },
          change: function (evt) {
            self.step.content = evt.editor.getData();
          },
        }
      });
      this.instance = CKEDITOR.instances['stepContent'];
    },
    setToolBarEffect() {
      const element = CKEDITOR.instances['stepContent'];
      const elementNewTop = document.getElementById('stepTop');
      element.on('contentDom', function () {
        this.document.on('click', function () {
          elementNewTop.classList.add('darkComposerEffect');
        });
      });
      element.on('contentDom', function () {
        this.document.on('keyup', function () {
          elementNewTop.classList.add('darkComposerEffect');
        });
      });
      $('#eLearningEditor').parent().click(() => {
        elementNewTop.classList.remove('darkComposerEffect');
        elementNewTop.classList.add('greyComposerEffect');
      });
      $('#eLearningEditor').parent().keyup(() => {
        elementNewTop.classList.remove('darkComposerEffect');
        elementNewTop.classList.add('greyComposerEffect');
      });
    },
    resetData() {
      this.initActualStepDone = false;
      //re-initialize data
      this.actualPersistedStep = {
        id: '',
        title: '',
        content: '',
        mediaLink: '',
        imageFileId: '',
        order: '',
        tutorialId: '',
      };
      this.step = {
        id: '',
        title: '',
        content: '',
        mediaLink: '',
        imageFileId: '',
        order: '',
        tutorialId: this.tutorial.id,
      };
      this.actualStep = {
        id: '',
        title: '',
        content: '',
        mediaLink: '',
        imageFileId: '',
        order: '',
        tutorialId: this.tutorial.id,
      };
      CKEDITOR.instances['stepContent'].setData('');
      this.initActualStepDone = true;
    },
    publishTutorial() {
      this.$tutoService.publishTutorial(this.tutorial).then(() => {
        window.open(`${eXo.env.portal.context}/${eXo.env.portal.portalName}/elearning-preview?spaceId=${eXo.env.portal.spaceId}&themeId=${this.theme.id}&tutorialId=${this.tutorial.id}`, '_blank');
      }).catch(e => {
        console.error('Error when publishing tutorial', e);
      });
    },
    archiveTutorial() {
      this.$tutoService.archiveTutorial(this.tutorial).catch(e => {
        console.error('Error when archiving tutorial', e);
      });
    },
    openSettings() {
      console.log('open Settings !');
    },
    preview() {
      window.open(`${eXo.env.portal.context}/${eXo.env.portal.portalName}/elearning-preview?spaceId=${eXo.env.portal.spaceId}&themeId=${this.theme.id}&tutorialId=${this.tutorial.id}`, '_blank');
    },
    addStep() {
      this.$root.$emit('step-added', this.step);
      this.persistStep(this.step, true);
    },
    addOnlineVideo() {
      console.log('Add Online Video !');
    },
    addPicture() {
      console.log('Add Picture !');
    },
    addToTutorialStepIds(stepId) {
      const stepIds = [];
      stepIds.push(...this.tutorial.stepsIds);
      stepIds.push(stepId);
      this.$set(this.tutorial, 'stepsIds', stepIds);
    },
    removeFromTutorialStepIds(stepId) {
      const stepIds = [];
      stepIds.push(...this.tutorial.stepsIds);
      const index = stepIds.findIndex(id => id === stepId);
      stepIds.splice(index, 1);
      this.$set(this.tutorial, 'stepsIds', stepIds);
    },
  }
};
</script>