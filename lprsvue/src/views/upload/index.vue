<template>
  <div class="app-container">
    <v-uploader :preview-width="400" :preview-height="300" @done="uploadDone" />
    <div style="position: relative;left:500px;top:-350px;">
      {{ plate }}<br>
      {{ color }}<br>
      {{ name }}
    </div>
  </div>
</template>
<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      plate: '',
      color: '',
      name: ''
    }
  },
  methods: {
    uploadDone(file) {
      this.plate = file[0].plate
      this.color = file[0].color
      this.name = file[0].name
    }
  }
}

</script>
