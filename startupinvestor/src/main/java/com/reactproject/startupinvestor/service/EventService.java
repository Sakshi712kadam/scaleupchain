package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.EventDTO;
import com.reactproject.startupinvestor.entities.Event;
import com.reactproject.startupinvestor.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    // Create Event
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setDescription(eventDTO.getDescription());
        event = eventRepository.save(event);
        return convertToDTO(event);
    }

    // Get All Events
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get Event By ID
    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        return convertToDTO(event);
    }

    // Update Event
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setDescription(eventDTO.getDescription());
        event = eventRepository.save(event);
        return convertToDTO(event);
    }

    // Delete Event
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // Helper Method to Convert Entity to DTO
    private EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDate(event.getDate());
        eventDTO.setDescription(event.getDescription());
        return eventDTO;
    }
}
