<template>
    <div class="wrapper">
        <div class="section page-header header-filter" :style="headerStyle">
            <div class="container">
                <div class="md-layout">
                    <div class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto">
                        <login-card header-color="green">
                            <h4 slot="title" class="card-title">회원 가입</h4>
                            <p slot="description" class="description">Or Be Classical</p>
                            <md-field class="md-form-group" slot="inputs">
                                <md-icon>face</md-icon>
                                <label>ID</label>
                                <md-input v-model="id"></md-input>
                            </md-field>
                            <md-field class="md-form-group" slot="inputs">
                                <md-icon>supervisor_account</md-icon>
                                <label>NickName</label>
                                <md-input v-model="nickname" ></md-input>
                            </md-field>
                            <md-field class="md-form-group" slot="inputs">
                                <md-icon>lock</md-icon>
                                <label>Password</label>
                                <md-input v-model="password"></md-input>
                            </md-field>
                            <md-button slot="footer" class="md-simple md-success md-lg">
                                Get Started
                            </md-button>
                        </login-card>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { LoginCard } from "@/components";
import { mapGetters } from 'vuex';

export default {
    components: {
        LoginCard
    },
    bodyClass: "login-page",
    data() {
        return {
        id: null,
        nickname: null,
        password: null
        };
    },
    props: {
        header: {
        type: String,
        default: require("@/assets/img/profile_city.jpg")
        }
    },
    computed: {
        headerStyle() {
            return {
                backgroundImage: `url(${this.header})`
            };
        },
        ...mapGetters(['duplicateCheck'])
    },
    methods : {
        duplicateCheck : function(type){
            var data = {
                val : type
            };
            if(type === 'id'){
                data["content"] = this.id;
            }else{
                data["content"] = this.nickname
            }
            this.$store.dispatch('duplicateCheck', data);
        }
    }
};
</script>

<style lang="css"></style>
