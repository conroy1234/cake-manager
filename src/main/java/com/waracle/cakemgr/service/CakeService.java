
package com.waracle.cakemgr.service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;

@Service
public class CakeService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	CakeRepository cakeRepository;

	ObjectMapper mapper = new ObjectMapper();

	public Iterable<CakeEntity> findAll() {
		return cakeRepository.findAll();
	}

	public CakeEntity findById(int id) {
		return cakeRepository.findById(id).get();
	}

	public CakeEntity createNewCake(CakeEntity entity) {
		return cakeRepository.save(entity);
	}

	public List<CakeEntity> findAllCakesFromCjonFile() {
		List<CakeEntity> cakes = null;
		try (InputStream inputStream = new URL(
				"https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
						.openStream();) {
			TypeReference<List<CakeEntity>> typeReference = new TypeReference<List<CakeEntity>>() {
			};
			cakes = mapper.readValue(inputStream, typeReference);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cakes;

	}

	public CakeEntity createCake(CakeEntity cake) {	
		try (InputStream inputStream = new URL(
				"https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
						.openStream();) {			
			String filePath = String.valueOf(inputStream);
			mapper.writeValue(new File(filePath), cake);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//logger.info("cake {}, {}, {}", cake.getTitle(), cake.getDesc(), cake.getImage());
		return createNewCake(cake);
	}

}
