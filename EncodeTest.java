import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

class EncodeTest {

	@Test
	void test() {
		Encode e = new Encode();
		String filename = "D:\\test.tsv";

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		try {

			e.countFrequency(filename, map);

			PriorityQueue<HuffNode> pri = new PriorityQueue<HuffNode>();

			if (e.frequencyVisible) map.forEach((k,v) -> System.out.println("character: "+k+" frequency"+v));
			map.forEach((c, f) -> pri.add(new HuffNode(c.charValue(), f.intValue())));

			while (pri.size() != 1) {
				HuffNode h = new HuffNode();

				HuffNode l = pri.poll();

				HuffNode r = pri.poll();

				h.setFreq(l.getFreq() + r.getFreq());
				h.setLeft(l);
				h.setRight(r);

				pri.add(h);
			}

			HuffNode root = pri.peek();

			HashMap<Character, String> encoder = new HashMap<Character, String>();

			e.printCode(root, "", encoder);

			e.encode(encoder, filename);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		assertEquals(e.encoded,"1001001011111110110101110101011111110110011000001011110010111011111010110101110010110101110101000001110001101000000000110010111000000011100000110000100000110111110111000100011110111110000000101111101011110001011101100000011000010010111001111100001010000010111011110010000010000001000011100011100000010111101000110000011100011010000001011110100011000000111100101111001111100001111010100100101110000111101010011110011111000101100101001011100010110010111100111110001011010100100101110001011010100111100111111000111001010010111100011100101111001111110001110101001001011110001110101001111001111110010011001010010111100100110010111100111111001001101010010010111100100110101001111001111110010110110010100101111001011011001011110011111100101101101010010010111100101101101010011110011111100110010100101111001100101111001111110011010100100101111001101010011110011111101101100101100100110010111100111111001001101010010010111100100110101001111001111110010110110010100101111001011011001011110011111100101101101010010010111100101101101010011110011111100110010100001111001011110011111000011110101001001011110001110010111100111111000111010100100101111001001101010011110011111100101101100101001011110011010100111100111111011011001010001011001011110011111000101101010010010111000101101010011110011111100011100101001011110001110101001111001111110010011001010001011001011110011111000101101010010010111000101101010011110011111100011100101001011110001110010111100111111000111010100100101111000111010100111100111111001001100101001011110010011001011110011111100100110101001");
	}

}
