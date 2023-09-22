export const batchSubmitrecipients = ({fileJson, left}) => {
    const recipients = fileJson.slice(1).map((personArr) => {
        // personArr: ["Jenny", "Han"]
        const personObj = left.reduce((acc, cur, index) => {
            acc = {
                ...acc,
                [cur]: personArr[index]
            }
            return acc
        }, {})
        return personObj
    });
    console.log({recipients})
    return recipients;
}