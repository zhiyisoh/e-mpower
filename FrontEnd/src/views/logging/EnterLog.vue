<template>
  <div class="loge-waste">
    <h1>Hello {{this.$store.state.auth.user.username}}, ready to recycle?</h1>
    <!-- <input type="file" accept="image/*" @change="uploadImage($event)" id="file-input"> -->
    <!-- code to allow user to upload image -->
  </div>
</template>
  
<script>
methods: {
  //method to upload image, still figuring it out
  // uploadImage(event) {

  //   const URL = 'http://foobar.com/upload';

  //   let data = new FormData();
  //   data.append('name', 'my-picture');
  //   data.append('file', event.target.files[0]);

  //   let config = {
  //     header: {
  //       'Content-Type': 'image/png'
  //     }
  //   }

  //   axios.put(
  //     URL,
  //     data,
  //     config
  //   ).then(
  //     response => {
  //       console.log('image upload response > ', response)
  //     }
  //   )
  // }
}
</script>