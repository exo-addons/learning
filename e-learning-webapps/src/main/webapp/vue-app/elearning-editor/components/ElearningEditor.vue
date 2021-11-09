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
                @click="postTutorial()">
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
                <div class="add-step-wrapper">
                  <div class="add-step">
                    <div>
                      <v-icon large>add</v-icon>
                    </div>
                  </div>
                  <span>{{ $t('addon.elearning.editor.label.addStep') }}</span>
                </div>
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
                      :placeholder="stepContentPlaceHolder"
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
        order: 1
      },
      actualStep: {},
      spaceId: '',
      tutorialId: '',
      srcImageStep: '/exo-elearning/images/tutorial.png',
      eLearningFormTitle: 'Tutorial 1',
      initActualStepDone: false,
      initDone: false,
    };
  },
  computed: {
    stepTitlePlaceHolder() {
      return this.$t('addon.elearning.editor.label.step.title', {0: this.step.order});
    },
    stepContentPlaceHolder() {
      return this.$t('addon.elearning.editor.label.step.content', {0: this.step.order});
    }
  },
  created() {
    console.log('E-Learning Editor Created !!!');
    const queryPath = window.location.search;
    console.log('### queryPath: ', queryPath);
    const urlParams = new URLSearchParams(queryPath);
    console.log('### urlParams: ', urlParams);
    if (urlParams.has('spaceId')) {
      this.spaceId = urlParams.get('spaceId');
    }
    if (urlParams.has('tutorialId')) {
      this.tutorialId = urlParams.get('tutorialId');
    }
    console.log('*** this.spaceId: ', this.spaceId);
    console.log('*** this.tutorialId: ', this.tutorialId);
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
    fillStep(data) {
      this.initActualStepDone = false;
      if (data) {
        this.step = data;
        CKEDITOR.instances['stepContent'].setData(data.content);
        this.actualStep = {
          id: this.step.id,
          title: this.step.title,
          content: this.step.content,
          author: this.step.author,
          owner: this.step.owner,
          toBePublished: this.step.toBePublished,
        };
      }
    },
    initCKEditor: function() {
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
          { name: 'format', items: ['Format'] },
          { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'RemoveFormat'] },
          { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Blockquote' ] },
          { name: 'fontsize', items: ['FontSize'] },
          { name: 'colors', items: [ 'TextColor' ] },
          { name: 'align', items: [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] },
          { name: 'insert' },
          { name: 'links', items: [ 'simpleLink','InsertOptions'] },
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


            CKEDITOR.instances['stepContent'].contextMenu.addListener( function( element ) {
              if ( element.getAscendant( 'table', true ) ) {
                return {
                  tableProperties: CKEDITOR.TRISTATE_ON
                };
              }
            });
            CKEDITOR.instances['stepContent'].addCommand('tableProperties', {
              exec: function() {
                if (CKEDITOR.instances['stepContent'].elementPath() && CKEDITOR.instances['stepContent'].elementPath().contains( 'table', 1 )){
                  const tableSummary = CKEDITOR.instances['stepContent'].elementPath().contains( 'div', 1 ).$.firstChild.innerText;
                  const table=CKEDITOR.instances['stepContent'].elementPath().contains( 'table', 1 ).getAttributes();
                  self.$refs.stepTablePlugins.open(table, tableSummary);
                }

              }
            });
            $(CKEDITOR.instances['stepContent'].document.$)
              .find('.atwho-inserted')
              .each(function() {
                $(this).on('click', '.remove', function() {
                  $(this).closest('[data-atwho-at-query]').remove();
                });
              });

            // self.$root.$applicationLoaded();
            // window.setTimeout(() => self.setFocus(), 50);
          },
          change: function (evt) {
            self.step.content = evt.editor.getData();
          },
        }
      });
      this.instance =CKEDITOR.instances['stepContent'];
    },
    setToolBarEffect() {
      const element = CKEDITOR.instances['stepContent'] ;
      const elementNewTop = document.getElementById('stepTop');
      element.on('contentDom', function () {
        this.document.on('click', function(){
          elementNewTop.classList.add('darkComposerEffect');
        });
      });
      element.on('contentDom', function () {
        this.document.on('keyup', function(){
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
    postTutorial() {
      console.log('Post Tutorial !!!');
    },
    archiveTutorial() {
      console.log('archive Tutorial !');
    },
    openSettings() {
      console.log('open Settings !');      
    },
    preview() {
      console.log('PREVIEW !');
    },
    addOnlineVideo() {
      console.log('Add Online Video !');
    },
    addPicture() {
      console.log('Add Picture !');
    }
  }
};
</script>