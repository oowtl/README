<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>Q&A</h1>
              <br>
              <br>
              <h3 class="description">
                문의가 있으면 아래 이메일로 문의주세요
              </h3>
              <br>
              <br>
              <h3>Readme@saffy.com</h3>
              <br>
              <br>
              
            </div>
          </div>
        </div>
      </div>
    </parallax>
  </div>


</template>

<script>





export default {
  components: {
    


  },
  name: "QA",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/도서관1.jpg")
    },
    book: {
      type: String,
      default: require("@/assets/img/책2.png")
    },
    landing: {
      type: String,
      default: require("@/assets/img/landing.jpg")
    },
    profile: {
      type: String,
      default: require("@/assets/img/profile.jpg")
    },
    teamImg1: {
      type: String,
      default: require("@/assets/img/faces/avatar.jpg")
    },
    teamImg2: {
      type: String,
      default: require("@/assets/img/faces/christian.jpg")
    },
    teamImg3: {
      type: String,
      default: require("@/assets/img/faces/kendall.jpg")
    }
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      leafShow: false
    };
  },
  methods: {
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`
      };
    },
    bookImage() {
      return {
        backgroundImage: `url(${this.book})`
      };
    }
  },
  mounted() {
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.leafActive);
  }
};
</script>
<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
a:link{
  color: black;
  text-decoration: none;
}
</style>
