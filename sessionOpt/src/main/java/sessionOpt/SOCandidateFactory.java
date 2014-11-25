package sessionOpt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.CandidateFactory;

import sessionOpt.entities.Room;
import sessionOpt.entities.Session;
import sessionOpt.entities.Solution;



public class SOCandidateFactory implements CandidateFactory<Solution> {
	
	private List<Room> rooms;
	private List<Session> sessions;
	private List<Date> startDates;
	
	public SOCandidateFactory(List<Room> rooms, List<Session> sessions, List<Date> startDates){
		this.rooms = rooms;
		this.sessions = sessions;
		this.startDates = startDates;
	}
 
	@Override
	public List<Solution> generateInitialPopulation(int populationSize, Random rng) {
		List<Solution> result = new ArrayList<Solution>();
		for (int i = 0; i < populationSize; i++){
			result.add(generateRandomCandidate(rng));
		}
		return result;
	}

	@Override
	public List<Solution> generateInitialPopulation(int populationSize,
			Collection<Solution> seedCandidates, Random rng) {
		// TODO What for?
		return generateInitialPopulation(populationSize, rng);
	}

	@Override
	public Solution generateRandomCandidate(Random rng) {
		Solution result = new Solution(rooms, startDates);
		for (Session session: sessions){
			int rnd = rng.nextInt(result.getAmountOfSlots());
			while (result.getSlot(rnd).getSession() != null){
				rnd = rng.nextInt(result.getAmountOfSlots());
			}
			result.getSlot(rnd).setSession(session);

		}
		
		return result;
	}

}
