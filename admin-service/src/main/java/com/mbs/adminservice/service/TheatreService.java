package com.mbs.adminservice.service;


import com.mbs.adminservice.model.Theatre;

import java.util.List;

public interface TheatreService {

    List<Theatre> getAllTheatres();

    Theatre getTheatreById(Long theatre_id);

    Theatre addTheatre(Theatre newTheatre);

    Theatre updateTheatre(Theatre updatedTheatre, Long theatre_id);

    void deleteTheatreById(Long theatre_id);
}
