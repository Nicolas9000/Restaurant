// package com.example.api.models;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "menu_dish")

// @Data
// @NoArgsConstructor
// @AllArgsConstructor

// public class MenuDish {
//     @JsonIgnore
//     @OneToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "menu_id")
//     private Menu menu;

//     @JsonIgnore
//     @OneToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "dish_id")
//     private Dish dish;
// }
