<template>
  <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand">Marvel</a>
          <form class="d-flex">
            <input class="form-control me-2" 
                type="search" placeholder="Поиск" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Все</button>
          </form>
        </div>
      </nav>
</template>

<script>
    export default {
        name: "AppHeader",
        data() {
            return {
            }
        },
    }
</script>

<style scoped>

</style>
