package dangeon.latest.scene.action.otog.ready.select.select;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import dangeon.latest.scene.action.otog.value.ValueHolder;

public class Otog_Select_Initializer {
	public void initContents(ArrayList<Otog_Select_Content_Selection> content,
			ValueHolder VH) {
		File files[] = new File("otog/csv").listFiles();
		for (File file : files) {
			if (!file.getName().contains(".csv"))
				continue;
			FileReader filereader;
			BufferedReader br = null;
			try {
				filereader = new FileReader(file);
				br = new BufferedReader(filereader);
				String name = file.getName();
				String regex = name.substring(name.lastIndexOf("."))
						.replaceAll("\\.", "\\\\.");
				name = name.replaceFirst(regex, ".properties");
				StringTokenizer st = new StringTokenizer(br.readLine(), ",");
				content.add(new Otog_Select_Content_Selection(st.nextToken(),
						Integer.valueOf(st.nextToken()), Integer.valueOf(st
								.nextToken()), Long.valueOf(st.nextToken()), st
								.nextToken(), file, "otog/prop/".concat(name),
						VH));
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			boolean flag = VH != null;
			boolean flag2 = content.get(content.size() - 1).FLAG_CLEARED == -1;
			if (flag && flag2) {
				content.remove(content.size() - 1);
			}
		}
	}
}
