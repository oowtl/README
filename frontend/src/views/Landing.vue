<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <h1 class="title">BEST BOOKS</h1>
            <h4>
              We can watch the movie we want.
            </h4>
            <br />
            <md-button
              href="/login"
              class="md-danger md-lg"
              target="_blank"
              ><i class="fas fa-play"></i> Login</md-button
            >
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <div class="md-layout">
            <div class="section text-center">
              <div class="container">
                <h2 class="title">공포</h2>
                <div class="team">
                  <div class="md-layout">
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg1"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            대불호텔의 유령
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg2"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            경계선
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg3"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            호러북클럽이 뱀파이어를 처리하는 방식
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="container">
                <h2 class="title">로맨스</h2>
                <div class="team">
                  <div class="md-layout">
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg11"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            홍천기
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg22"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            옷소매 붉은 끝동
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg33"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            하루하라 미나와 순정
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="container">
                <h2 class="title">SF</h2>
                <div class="team">
                  <div class="md-layout">
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg111"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            지구끝에 온실
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg222"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            멋진신세계
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg333"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            프로젝트 헤일메리
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="container">
                <h2 class="title">추리</h2>
                <div class="team">
                  <div class="md-layout">
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg1111"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            백조와 박쥐
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg2222"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            일몰의 저편
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                    <div class="md-layout-item md-medium-size-33 md-small-size-100">
                      <div class="team-player">
                        <md-card class="md-card-plain">
                          <div class="md-layout-item md-size-50 mx-auto">
                            <img
                              :src="teamImg3333"
                              alt="Thumbnail Image"
                              class="img-raised rounded-circle img-fluid"
                            />
                          </div>
                          <h4 class="card-title">
                            요리코를 위하여
                            <br />
                          </h4>
                        </md-card>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            </div>
          </div>
        </div>
      </div>
<div class="section">
        <div class="container">
          <div class="md-layout">
            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center">
              <h2 class="title text-center">즐겨주셔서 감사합니다</h2>
              <h5 class="description">
                WE CAN ENJOY BOOKS
              </h5>
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  bodyClass: "landing-page",
  props: {
    header: {
      type: String,
      default: require("@/assets/img/도서관1.jpg")
    },
    teamImg1: {
      type: String,
      default: require("@/assets/img/faces/공포1.jpg")
    },
    teamImg2: {
      type: String,
      default: require("@/assets/img/faces/공포2.jpg")
    },
    teamImg3: {
      type: String,
      default: require("@/assets/img/faces/공포3.jpg")
    },
    teamImg11: {
      type: String,
      default: require("@/assets/img/faces/로맨스1.jpg")
    },
    teamImg22: {
      type: String,
      default: require("@/assets/img/faces/로맨스2.jpg")
    },
    teamImg33: {
      type: String,
      default: require("@/assets/img/faces/로맨스3.jpg")
    },
    teamImg111: {
      type: String,
      default: require("@/assets/img/faces/SF1.jpg")
    },
    teamImg222: {
      type: String,
      default: require("@/assets/img/faces/SF2.jpg")
    },
    teamImg333: {
      type: String,
      default: require("@/assets/img/faces/SF3.jpg")
    },
    teamImg1111: {
      type: String,
      default: require("@/assets/img/faces/추리1.jpg")
    },
    teamImg2222: {
      type: String,
      default: require("@/assets/img/faces/추리2.jpg")
    },
    teamImg3333: {
      type: String,
      default: require("@/assets/img/faces/추리3.jpg")
    },
  },
  
  data() {
    return {
      name: null,
      email: null,
      message: null
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="scss" scoped>
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
}
</style>
