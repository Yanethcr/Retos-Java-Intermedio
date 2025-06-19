package com.bedu.inventariodos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class InventariodosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventariodosApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(ProductoRepository productoRepo, CategoriaRepository categoriaRepo, MarcaRepository marcaRepo) {
		return (args) -> {
			Categoria tecnologia = new Categoria("Tecnología");
			categoriaRepo.save(tecnologia);

			Marca apple = new Marca("Apple");
			Marca samsung = new Marca("Samsung");
			marcaRepo.save(apple);
			marcaRepo.save(samsung);

			productoRepo.save(new Producto("iPhone 15", "Smartphone", 25000.00, tecnologia, apple));
			productoRepo.save(new Producto("iPad Pro", "Tablet", 30000.00, tecnologia, apple));
			productoRepo.save(new Producto("Galaxy S23", "Smartphone", 22000.00, tecnologia, samsung));
			productoRepo.save(new Producto("Smart TV", "Televisor", 18000.00, tecnologia, samsung));

			System.out.println("📚 Productos por marca:");
			marcaRepo.findAll().forEach(marca -> {
				System.out.println("🏷️ " + marca.getNombre() + ":");
				productoRepo.findAll().stream()
						.filter(p -> p.getMarca().getId().equals(marca.getId()))
						.forEach(p -> System.out.println("   - " + p.getNombre()));
			});
		};

	}
}
