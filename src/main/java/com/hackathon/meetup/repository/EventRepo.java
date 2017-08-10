package com.hackathon.meetup.repository;

import com.hackathon.meetup.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David Turk on 8/9/17.
 */
public interface EventRepo extends JpaRepository<Event, Integer> {
}
