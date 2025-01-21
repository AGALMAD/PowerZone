package com.PowerZone.PowerZone.Repository

import com.PowerZone.PowerZone.Models.Participation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipationRepository : CrudRepository<Participation, String>