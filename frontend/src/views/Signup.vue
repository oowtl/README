<template>
    <div class="wrapper">
        <div class="section page-header header-filter" :style="headerStyle">
            <div class="container">
                <div class="md-layout">
                    <div class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto">
                        <login-card header-color="green">
                            <h4 slot="title" class="card-title">회원 가입</h4>
                            <p slot="description" class="description">Or Be Classical</p>

                            <md-field slot="inputs" :class="[!getSignupCheck.id.condition.flag && !getSignupCheck.id.duplicate.flag ? 'md-error' : 'md-valid']">
                                <md-icon>face</md-icon>
                                <label>ID</label>
                                <md-input v-model="id" type="text" @keyup="duplicateCheck('id')"></md-input>
                                <span class="md-helper-text">{{getSignupCheck.id.condition.msg}}</span>
                                <div v-if="getSignupCheck.id.condition.flag && getSignupCheck.id.duplicate.flag">
                                    <md-icon>done</md-icon>
                                </div>
                                <div v-else>
                                    <md-icon>clear</md-icon>
                                </div>
                            </md-field>


                            <md-field slot="inputs" :class="[!getSignupCheck.nickname.condition.flag && !getSignupCheck.nickname.duplicate.flag ? 'md-error' : 'md-valid']">
                                <md-icon>supervisor_account</md-icon>
                                <label>NickName</label>
                                <md-input v-model="nickname" type="text" @keyup="duplicateCheck('nickname')"></md-input>
                                <span class="md-helper-text">{{getSignupCheck.nickname.condition.msg}}</span>
                                <div v-if="getSignupCheck.nickname.condition.flag && getSignupCheck.nickname.duplicate.flag">
                                    <md-icon>done</md-icon>
                                </div>
                                <div v-else>
                                    <md-icon>clear</md-icon>
                                </div>
                            </md-field>
                            

                            <md-field slot="inputs" :class="[!getSignupCheck.password.condition.flag ? 'md-error' : 'md-valid']">
                                <md-icon>lock</md-icon>
                                <label>Password</label>
                                <md-input v-model="password" type="text" @keyup="duplicateCheck('password')"></md-input>
                                <span class="md-helper-text">{{getSignupCheck.password.condition.msg}}</span>
                                <div v-if="getSignupCheck.password.condition.flag">
                                    <md-icon>done</md-icon>
                                </div>
                                <div v-else>
                                    <md-icon>clear</md-icon>
                                </div>
                                <span>{{getSignupCheck}}</span>
                            </md-field>
                            
                            <md-button slot="footer" class="md-simple md-success md-lg" @click="duplicateCheck('password')">
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
            id: "",
            nickname: "",
            password: "",
            icon : {
                id : "",
                nickname : "",
                password : ""
            }
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
        messageClass () {
            return {
                'md-invalid' : !this.getSignupCheck.password.condition.flag
            }
        },
        ...mapGetters(['getSignupCheck'])
    },
    methods : {
        conditionCheck : function(type){
            if(type === 'id'){ // ID 조건
                return this.id.length > 4 ? true : false
            }else if(type === 'nickname'){ // 닉네임 조건
                return this.nickname.length > 3 ? true : false
            }else{ // Password 조건
                return this.password.length > 5 ? true : false
            }
        },
        duplicateCheck : function(type){
            var data = {
                condition : this.conditionCheck(type),
                val : type,
            };
            if(type === 'id'){
                data["content"] = this.id;
            }else {
                data["content"] = this.nickname
            }
            this.$store.dispatch('signupCheck', data);
        }
    }
};
</script>

<style lang="css"></style>
