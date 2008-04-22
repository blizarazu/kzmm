package lab1;

import java.util.Vector;


import magick.ColorspaceType;
import magick.MagickException;
import magick.MagickImage;
import magick.NoiseType;

public class MagickManager {

	private Vector<MagickImages> vTrainIrudiFiltroekin;	
	private int kontTrain;

	private Vector<MagickImages> vTestIrudiFiltroekin;
	private int kontTest;

	public MagickManager() {
		vTrainIrudiFiltroekin = new Vector<MagickImages>();
		vTestIrudiFiltroekin = new Vector<MagickImages>();
		kontTrain = 0;
		kontTest = 0;
	}

	private MagickImages blur(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Blur");
		irudiak.setAtributuak("radius:0.25, sigma:10");
		for (MagickImage irudia : vIrudiak)
			irudiak.addElement(irudia.blurImage(0.25, 10));
		return irudiak;
	}

	private MagickImages sharpen(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Sharpen");
		irudiak.setAtributuak("radius: 0.25, sigma: 85");
		for (MagickImage irudia : vIrudiak)
			irudiak.addElement(irudia.sharpenImage(0.25, 85));
		return irudiak;
	}

	private MagickImages threshold(Vector<Irudia> vIrudiak)
			throws MagickException {
		MagickImages irudiak = new MagickImages("Threshold");
		irudiak.setAtributuak("threshold: 10");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			if (lag.thresholdImage(10))
				irudiak.addElement(lag);
		}
		return irudiak;
	}

	private MagickImages addNoise(Vector<Irudia> vIrudiak)
			throws MagickException {
		MagickImages irudiak = new MagickImages("Add Noise");
		irudiak.setAtributuak("noise type: Gaussian Noise");
		for (MagickImage irudia : vIrudiak)
			irudiak.addElement(irudia.addNoiseImage(NoiseType.GaussianNoise));
		return irudiak;
	}

	private MagickImages charcoal(Vector<Irudia> vIrudiak)
			throws MagickException {
		MagickImages irudiak = new MagickImages("Charcoal");
		irudiak.setAtributuak("radius: 4, sigma: 1");
		for (MagickImage irudia : vIrudiak)
			irudia.charcoalImage(4, 1);
		return irudiak;
	}

	private MagickImages contrast(Vector<Irudia> vIrudiak)
			throws MagickException {
		MagickImages irudiak = new MagickImages("Contrast");
		irudiak.setAtributuak("sharpen: true");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			if (lag.contrastImage(true))
				irudiak.addElement(lag);
		}
		return irudiak;
	}

	private MagickImages edge(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Edge");
		irudiak.setAtributuak("radius: 3");
		for (MagickImage irudia : vIrudiak)
			irudia.edgeImage(3);
		return irudiak;
	}

	private MagickImages emboss(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Emboss");
		irudiak.setAtributuak("radius: 3, sigma: 0,25");
		for (MagickImage irudia : vIrudiak)
			irudia.embossImage(3, 0.25);
		return irudiak;
	}

	private MagickImages equalize(Vector<Irudia> vIrudiak)
			throws MagickException {
		MagickImages irudiak = new MagickImages("Equalize");
		// irudiak.setAtributuak("");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			if (lag.equalizeImage())
				irudiak.addElement(lag);
		}
		return irudiak;
	}

	private MagickImages level(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Level");
		irudiak.setAtributuak("levels: black");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			if (lag.levelImage("black"))
				irudiak.addElement(lag);
		}
		return irudiak;
	}
	
	private MagickImages negative(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Negative");
		irudiak.setAtributuak("grayscale: 1");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			if (lag.negateImage(1))
				irudiak.addElement(lag);
		}
		return irudiak;
	}
	
	private MagickImages normalize(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Normalize");
		//irudiak.setAtributuak("");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			if (lag.normalizeImage())
				irudiak.addElement(lag);
		}
		return irudiak;
	}
	
	private MagickImages segment(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Segment");
		irudiak.setAtributuak("colorspace: GRAY Colorspace, cluster threshold: 0,25, smoothing threshold: 1,5");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			lag.segmentImage(ColorspaceType.GRAYColorspace, 0.25, 1.5);
			irudiak.addElement(lag);
		}
		return irudiak;
	}
	
	private MagickImages solarize(Vector<Irudia> vIrudiak) throws MagickException {
		MagickImages irudiak = new MagickImages("Solarize");
		irudiak.setAtributuak("threshold: 10");
		MagickImage lag;
		for (MagickImage irudia : vIrudiak) {
			lag = irudia.cloneImage(0, 0, false);
			lag.solarizeImage(10);
			irudiak.addElement(lag);
		}
		return irudiak;
	}
	

	public void filtroaAplikatu(Vector<Irudia> vTrainIrudiak,
			Vector<Irudia> vTestIrudiak) throws MagickException {

		this.vTrainIrudiFiltroekin = new Vector<MagickImages>();
		this.kontTrain = 0;
		this.vTestIrudiFiltroekin = new Vector<MagickImages>();
		this.kontTest = 0;

		vTrainIrudiFiltroekin.addElement(blur(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(blur(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(sharpen(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(sharpen(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(threshold(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(threshold(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(addNoise(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(addNoise(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(charcoal(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(charcoal(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(contrast(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(contrast(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(edge(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(edge(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(emboss(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(emboss(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(equalize(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(equalize(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(level(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(level(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(negative(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(negative(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(normalize(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(normalize(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(segment(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(segment(vTestIrudiak));
		
		vTrainIrudiFiltroekin.addElement(solarize(vTrainIrudiak));
		vTestIrudiFiltroekin.addElement(solarize(vTestIrudiak));


//		for (MagickImage iTest : vTestIrudiak) {
//			// Blur
//			testIrudiak.addElement(iTest.blurImage(0.25, 10));
//
//			// Sharpen
//			testIrudiak.addElement(iTest.sharpenImage(0.25, 85));
//
//			// Threshold
//			lag = iTest.cloneImage(0, 0, false);
//			if (lag.thresholdImage(10))
//				testIrudiak.addElement(lag);
//
//			// Add Noise
//			testIrudiak
//					.addElement(iTest.addNoiseImage(NoiseType.GaussianNoise));
//
//			// Charcoal
//			testIrudiak.addElement(iTest.charcoalImage(4, 1));
//
//			// Contrast
//			lag = iTest.cloneImage(0, 0, false);
//			if (lag.contrastImage(true))
//				testIrudiak.addElement(lag);
//
//			// Edge
//			testIrudiak.addElement(iTest.edgeImage(3));
//
//			// Emboss
//			testIrudiak.addElement(iTest.embossImage(3, 0.25));
//
//			// Equalize
//			lag = iTest.cloneImage(0, 0, false);
//			if (lag.equalizeImage())
//				testIrudiak.addElement(lag);
//
//			// Level
//			lag = iTest.cloneImage(0, 0, false);
//			if (lag.levelImage("black"))
//				testIrudiak.addElement(lag);
//
//			// Negative
//			lag = iTest.cloneImage(0, 0, false);
//			if (lag.negateImage(1))
//				testIrudiak.addElement(lag);
//
//			// Normalize
//			lag = iTest.cloneImage(0, 0, false);
//			if (lag.normalizeImage())
//				testIrudiak.addElement(lag);
//
//			// Segment
//			lag = iTest.cloneImage(0, 0, false);
//			lag.segmentImage(ColorspaceType.GRAYColorspace, 0.25, 1.5);
//			testIrudiak.addElement(lag);
//
//			// Solarize
//			lag = iTest.cloneImage(0, 0, false);
//			lag.solarizeImage(10);
//			testIrudiak.addElement(lag);
//
//			vTestIrudiFiltroekin.addElement(testIrudiak);
//		}
	}

	public void scale(Vector<Irudia> ir) throws MagickException {
		// ImageInfo info = new ImageInfo();
		// MagickImage image = new MagickImage(new
		// ImageInfo("BAIimg_00001.pgm"));
		// MagickImage bigger = image.scaleImage(1200, 900);
		// bigger.setFileName("froga/bigger.pgm");
		// bigger.writeImage(info);

//		for (Irudia i : ir) {
//			// ImageInfo info = new ImageInfo();
//			// MagickImage mi = i.scaleImage(2000, 900);
//			// mi.setFileName("frogaScale/"+ i.getFitxIzen());
//			// //System.out.println(1.1 + mi.getFileName() + " == " +
//			// info.getFileName());
//			// mi.writeImage(info);
//			// System.out.println(1.2);
//			// try {
//			// //i.irudiaGorde("frogaScale");
//			// } catch (IOException e) {
//			// // TODO Auto-generated catch block
//			// e.printStackTrace();
//			// }
//		}
	}

	private class MagickImages extends Vector<MagickImage> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private final String filtroa;

		private String atributuak;

		public MagickImages(String filtroa) {
			super();
			this.filtroa = filtroa;
		}

		public String getFiltroa() {
			return filtroa;
		}

		public String getAtributuak() {
			return atributuak;
		}

		public void setAtributuak(String atributuak) {
			this.atributuak = atributuak;
		}
	}

	public Vector<MagickImages> getVTestIrudiFiltroekin() {
		return vTestIrudiFiltroekin;
	}

	public Vector<MagickImages> getVTrainIrudiFiltroekin() {
		return vTrainIrudiFiltroekin;
	}
}
