<template>
  <div class="main">
    <h1>Shortening Url Service</h1>
    <h3 v-if="error" class="warning">{{errorMessage}}</h3>
    <form>
      <label>URL: </label>
      <input type="text" name="originalUrl" id="originalUrl" v-model="originalUrl">
      <button type="button" @click="changeUrl">Change</button>
    </form>
    <br>
    <div>
      <span>Shorten URL: </span>
      <span>{{recivedShortenUrl}}</span><br>
      <span>Inquiry Count: </span>
      <span>{{inquiryCount}}</span>
    </div>
    <h2>Enter the Shorten URL</h2>
    <form>
      <label>Shorten URL: </label>
      <input type="text" name="shortenUrl" id="shortenUrl" v-model="shortenUrl">
      <button type="button" @click="goPage">Go!</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
import router from '../router/index';

export default {
  name: 'Main',
  data() {
    return {
      inquiryCount: 0,
      originalUrl: '',
      shortenUrl: '',
      receivedOriginalUrl: '',
      recivedShortenUrl: '',
      error: false,
      errorMessage: ''
    }
  },
  methods : {
    changeUrl : function() {
      if (this.originalUrl !== '') {
        axios.post("http://localhost:8080/short/url", {originalUrl: this.originalUrl})
        .then(response => {
          console.error(response);
          this.recivedShortenUrl = response.data.shortenUrl;
          this.inquiryCount = response.data.inquiryCount;
        })
        .catch(e => {
          console.error(e);
        });
      } else {
        this.error = true;
        this.errorMessage = "URL fields can't be empty!";
      }
    },

    goPage : function() {
      if (this.shortenUrl !== '') {
        axios.get("http://localhost:8080/short/url/"+this.shortenUrl)
        .then(response => {
          if(response.data !== '') {
            this.receivedOriginalUrl = response.data.originalUrl;
            window.location.href = this.receivedOriginalUrl;
          }
          else {
            this.error = true;
            this.errorMessage = "Url Data is not registerd.";
          }
        })
        .catch(e => {
          console.error(e);
        });
      }
      else {
        this.error = true;
        this.errorMessage = "Shorten URL fields can't be empty!";
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
  background-color: black;
}

h1, h2 {
  font-weight: normal;
}
h3 {
  color: red;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

input {
  display: 'block';
  width: 50%;
  font-family: sans-serif;
  font-size: 1em;
  margin: 0.5em;
}
</style>
